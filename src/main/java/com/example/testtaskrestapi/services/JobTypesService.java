package com.example.testtaskrestapi.services;

import com.example.testtaskrestapi.entity.JobTypes;

import java.util.Optional;

public interface JobTypesService {
    JobTypes saveOrUpdate(JobTypes jobData);
    Optional<JobTypes> getByJobTypes(JobTypes jobData);
}
