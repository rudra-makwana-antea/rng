package com.anteash.rng.helper;

import com.anteash.rng.issue.Issue;
import com.anteash.rng.issue.IssueService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReleaseNoteHelper {

    private final String AUTH_TOKEN = "perm:ZmVkZXJpY28ucnVzc28=.NDgtMA==.A0YeHi9qSonSr2DwRffG46PKso0w4H";
    private final Logger logger = LoggerFactory.getLogger(ReleaseNoteHelper.class);

    @Autowired
    IssueService issueService;
    @Autowired
    TemplatePreviewer templatePreviewer;
    @Value("${YouTrackAPI_bbox}")
    private String bboxAPI;
    @Value("${YouTrackAPI_im}")
    private String imAPI;

    public String createReleaseNote(String project, String version, String template) {
        List<Issue> issues = issueService.getIssuesByVersionAndProject(version, project);
        if (issues.isEmpty()) {
            issues = saveIssuesToDB(project, version);
        }
        String html = templatePreviewer.getPreviewByIssues(template, issues);
        return html;
    }

    public void refetchIssues(String project, String version) {
        issueService.deleteIssuesByProjectVersion(project, version);
        saveIssuesToDB(project, version);
    }

    public List<Issue> saveIssuesToDB(String project, String version) {
        List<Issue> issues = new ArrayList<>();
        List<String> issuesString = getIssuesFromAPI(project, version);
        JSONArray jsonArray = new JSONArray(issuesString.get(0));
        int size = jsonArray.length();

        for (int i = 0; i < size; i++) {
            Issue issue = new Issue();
            JSONObject object = jsonArray.getJSONObject(i);
            issue.setNumberInProject(object.getInt("numberInProject"));
            issue.setProject(project);
            issue.setVersion(version);
            JSONArray array = new JSONArray(object.get("customFields").toString());
            int length = array.length();
            for (int j = 0; j < length; j++) {
                JSONObject obj = array.getJSONObject(j);
                if (obj.getString("name").equalsIgnoreCase("Type")) {
                    issue.setTracker(obj.getJSONObject("value").getString("name"));
                } else if (obj.getString("name").equalsIgnoreCase("Release Notes")) { //For BBox project
                    issue.setReleaseNote(obj.getJSONObject("value").getString("text"));
                } else if (obj.getString("name").equalsIgnoreCase("Ticket")) { //For BBox Project
                    issue.setURL(obj.get("value").toString().equalsIgnoreCase("null") ? "" : String.valueOf(obj.get("value")));
                } else if (obj.getString("name").equalsIgnoreCase("Requires Release Notes")) { //For IM project && English release notes
                    issue.setReleaseNote(obj.getJSONObject("value").getString("value"));
                } else if (obj.getString("name").equalsIgnoreCase("URL Ticket")) { //For IM project
                    issue.setURL(obj.get("value").toString().equalsIgnoreCase("null") ? "" : String.valueOf(obj.get("value")));
                }
            }
            issue.setDate(new Date(System.currentTimeMillis()));
            issueService.save(issue);
            issues.add(issue);
        }

        return issues;
    }

    public List<String> getIssuesFromAPI(String project, String version) {
        List<String> apiResponse = new ArrayList<>();
        String API_url = project.equalsIgnoreCase("bbox") ? bboxAPI : imAPI;
        API_url = API_url.replace("{FIX_VERSION}", version);
        API_url = API_url.replace(" ", "%20");
        logger.info("Getting data from " + API_url);

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_url))
                .header("Authorization", "Bearer " + AUTH_TOKEN)
                .header("Accept", "application/json")
                .build();

        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(issue -> apiResponse.add(issue))
                .join();

        return apiResponse;
    }
}