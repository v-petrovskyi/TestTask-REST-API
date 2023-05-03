package com.example.testtaskrestapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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
    private String company_name;
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
    private List<JobTypes> job_types;
    private String location;
    private LocalDateTime created_at;

    public JobData(String slug, String company_name, String title, String description, boolean remote, String url, List<Tag> tags, List<JobTypes> job_types, String location, LocalDateTime created_at) {
        this.slug = slug;
        this.company_name = company_name;
        this.title = title;
        this.description = description;
        this.remote = remote;
        this.url = url;
        this.tags = tags;
        this.job_types = job_types;
        this.location = location;
        this.created_at = created_at;
    }
}
