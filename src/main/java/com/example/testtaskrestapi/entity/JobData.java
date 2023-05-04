package com.example.testtaskrestapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "jobData")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobData jobData = (JobData) o;
        return id == jobData.id && remote == jobData.remote && Objects.equals(slug, jobData.slug) && Objects.equals(companyName, jobData.companyName) && Objects.equals(title, jobData.title) && Objects.equals(description, jobData.description) && Objects.equals(url, jobData.url) && Objects.equals(tags, jobData.tags) && Objects.equals(jobTypes, jobData.jobTypes) && Objects.equals(location, jobData.location) && Objects.equals(createdAt, jobData.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, slug, companyName, title, description, remote, url, tags, jobTypes, location, createdAt);
    }
}
