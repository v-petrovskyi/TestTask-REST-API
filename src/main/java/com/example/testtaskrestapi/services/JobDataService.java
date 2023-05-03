package com.example.testtaskrestapi.services;

import com.example.testtaskrestapi.entity.JobData;

import java.util.List;

public interface JobDataService {
    JobData add(JobData jobData);
    List<JobData> getAll();

    List<JobData> getPage(int page, int size);
}
