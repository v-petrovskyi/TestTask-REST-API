package com.example.testtaskrestapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobTypes jobTypes = (JobTypes) o;
        return id == jobTypes.id && Objects.equals(jobType, jobTypes.jobType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, jobType);
    }
}