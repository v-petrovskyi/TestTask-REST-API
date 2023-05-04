package com.example.testtaskrestapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "jobTypes")
public class JobTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    private String jobType;

    public JobTypes(String jobType) {
        this.jobType = jobType;
    }
}