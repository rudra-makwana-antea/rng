package com.anteash.rng.issue;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity(name = "ISSUE")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private long id;
    @Column(name = "NUMBERINPROJECT")
    private int numberInProject;
    @Column(name = "RELEASENOTE")
    private String releaseNote;
    @Column(name = "TRACKER")
    private String tracker;
    @Column(name = "URL")
    private String URL;
    @Column(name = "DATE")
    private Date date;
    @Column(name = "PROJECT")
    private String project;
    @Column(name = "VERSION")
    private String version;

    public Issue(long id, int numberInProject, String releaseNote, String tracker, String URL, Date date, String project, String version) {
        this.id = id;
        this.numberInProject = numberInProject;
        this.releaseNote = releaseNote;
        this.tracker = tracker;
        this.URL = URL;
        this.date = date;
        this.project = project;
        this.version = version;
    }

    public Issue(long id, int numberInProject, String releaseNote, String tracker, Date date, String project, String version) {
        this.id = id;
        this.numberInProject = numberInProject;
        this.releaseNote = releaseNote;
        this.tracker = tracker;
        this.date = date;
        this.project = project;
        this.version = version;
    }
}
