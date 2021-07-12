package com.anteash.rng.template;

import lombok.*;

import javax.persistence.*;

@Data
@Entity(name = "TEMPLATE")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "HEADER")
    private String header;
    @Column(name = "ISSUE")
    private String issue;
    @Column(name = "FOOTER")
    private String footer;

    public Template(String name, String header, String issue, String footer) {
        this.name = name;
        this.header = header;
        this.issue = issue;
        this.footer = footer;
    }

    public Template(long id, String name, String header, String issue, String footer) {
        this.id = id;
        this.name = name;
        this.header = header;
        this.issue = issue;
        this.footer = footer;
    }
}