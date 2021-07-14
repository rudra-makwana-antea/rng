package com.anteash.rng.helper;

import com.anteash.rng.issue.Issue;
import com.anteash.rng.issue.IssueService;
import com.anteash.rng.template.Template;
import com.anteash.rng.template.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplatePreviewer {

    @Autowired
    IssueService issueService;
    @Autowired
    TemplateService templateService;

    private String issueValueReplacer(String issueTemplate, Issue issue) {
        String url = issue.getURL() == null ? "" : issue.getURL();
        String issueViewer = issueTemplate.replace("${numberInProject}", String.valueOf(issue.getNumberInProject()));
        issueViewer = issueViewer.replace("${releaseNote}", issue.getReleaseNote());
        issueViewer = issueViewer.replace("${tracker}", issue.getTracker());
        issueViewer = issueViewer.replace("${url}", url);
        issueViewer = issueViewer.replace("${date}", issue.getDate().toString());
        issueViewer = issueViewer.replace("${project}", issue.getProject());
        issueViewer = issueViewer.replace("${version}", issue.getVersion());

        return issueViewer;
    }

    public String getPreview(String templateName) {

        Template template = templateService.getTempalteByName(templateName);
        List<Issue> issues = issueService.getLastAddedIssues();

        String result = template.getHeader().replace("${version}", issues.get(0).getVersion());
        result = result.replace("${project}", issues.get(0).getProject());
        String issueTemplate = template.getIssue();

        for (Issue issue : issues) {
            result = result + issueValueReplacer(issueTemplate, issue);
        }

        result = result + template.getFooter();

        return result;
    }

    public String getPreview(String templateName, String projectVersion) {
        String version = projectVersion.split(" ")[1];
        String project = projectVersion.split(" ")[0];

        Template template = templateService.getTempalteByName(templateName);
        List<Issue> issues = issueService.getIssuesByVersionAndProject(version, project);

        String result = template.getHeader().replace("${version}", version);
        result = result.replace("${project}", project);
        String issueTemplate = template.getIssue();

        for (Issue issue : issues) {
            result = result + "&#10" + issueValueReplacer(issueTemplate, issue);
        }

        result = result + "&#10" + template.getFooter();

        return result;
    }

    public String getPreviewByIssues(String templateName, List<Issue> issues) {
        Template template = templateService.getTempalteByName(templateName);
        String result = template.getHeader().replace("${version}", issues.get(0).getVersion());
        result = result.replace("${project}", issues.get(0).getProject());
        String issueTemplate = template.getIssue();

        for (Issue issue : issues) {
            result = result + issueValueReplacer(issueTemplate, issue);
        }

        result = result + template.getFooter();

        return result;
    }
}
