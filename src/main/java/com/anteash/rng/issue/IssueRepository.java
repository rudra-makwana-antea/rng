package com.anteash.rng.issue;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IssueRepository extends CrudRepository<Issue, Long> {
    @Query("SELECT DISTINCT version FROM ISSUE")
    List<String> findDistinctVersions();

    @Query("SELECT DISTINCT project, version FROM ISSUE")
    List<List<String>> findVersionByDistinctProject();

    List<Issue> findByProjectAndVersion(String project, String version);

    void deleteAllByProjectAndVersion(String project, String version);

    Issue findTopByOrderByIdDesc();
}
