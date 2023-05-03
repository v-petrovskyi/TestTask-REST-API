package com.example.testtaskrestapi.services.impl;

import com.example.testtaskrestapi.entity.JobTypes;
import com.example.testtaskrestapi.entity.Tag;
import com.example.testtaskrestapi.repositories.JobTypesRepository;
import com.example.testtaskrestapi.services.JobTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobTypesServiceImpl implements JobTypesService {

    private final JobTypesRepository repository;

    @Autowired
    public JobTypesServiceImpl(JobTypesRepository repository) {
        this.repository = repository;
    }

    @Override
    public JobTypes add(JobTypes jobData) {
        return getByJobTypes(jobData).orElseGet(()->repository.save(jobData));
    }

    @Override
    public Optional<JobTypes> getByJobTypes(JobTypes jobData) {
        return repository.findByJobType(jobData.getJobType());
    }
}
