package com.example.testtaskrestapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
public class JobData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String slug;
    private String companyName;
    private String title;
    @Column(columnDefinition = "text")
    @ToString.Exclude
    private String description;
    private boolean remote;
    @Column(unique = true)
    private String url;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Tag> tags;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<JobTypes> jobTypes;
    private String location;
    private LocalDateTime createdAt;

    public JobData(String slug, String companyName, String title, String description, boolean remote, String url, List<Tag> tags, List<JobTypes> jobTypes, String location, LocalDateTime createdAt) {
        this.slug = slug;
        this.companyName = companyName;
        this.title = title;
        this.description = description;
        this.remote = remote;
        this.url = url;
        this.tags = tags;
        this.jobTypes = jobTypes;
        this.location = location;
        this.createdAt = createdAt;
    }
}
