package com.anteash.rng.issue;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class IssueService {
    public static final Logger logger = LoggerFactory.getLogger(IssueService.class);
    private final String pattern = "yyyy-MM-dd";
    @Autowired
    IssueRepository issueRepository;

    public List<Issue> getAllIssues() {
        List<Issue> issues = new ArrayList<>();
        issueRepository.findAll().forEach(issue -> issues.add(issue));
        return issues;
    }

    public List<String> getVersions() {
        List<String> versions = new ArrayList<>();
        issueRepository.findDistinctVersions().forEach(version -> versions.add(version));
        logger.info(versions.toString());
        return versions;
    }

    public List<String> getReleases() {
        List<String> releases = new ArrayList<>();
        issueRepository.findVersionByDistinctProject().forEach(release -> releases.add(release.get(0) + " " + release.get(1)));
        return releases;
    }

    @Transactional
    public List<Issue> getIssuesByVersionAndProject(String version, String project) {
        List<Issue> issues = new ArrayList<>();
        issueRepository.findByProjectAndVersion(project, version).forEach(issue -> issues.add(issue));
        return issues;
    }

    @Transactional
    public void deleteIssuesByProjectVersion(String project, String version) {
        issueRepository.deleteAllByProjectAndVersion(project, version);
    }

    public void save(Issue issue){
        issueRepository.save(issue);
    }

    public void extractFromJson(Issue issue) {
        save(issue);
    }

    @Transactional
    public List<Issue> getLastAddedIssues(){
        Issue issue = issueRepository.findTopByOrderByIdDesc();
        if(issue == null) {
            throw new NullPointerException();
        }
        List<Issue> issues = getIssuesByVersionAndProject(issue.getVersion(), issue.getProject());
        return issues;
    };
}
