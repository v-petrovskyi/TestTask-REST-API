package com.example.testtaskrestapi.services;

import com.example.testtaskrestapi.entity.JobData;

import java.util.List;
import java.util.Optional;

public interface JobDataService {
    JobData add(JobData jobData);
    List<JobData> getAll();
    List<JobData> getPage(int page, int size);
    Optional<JobData> findBySlug(String slug);
}
