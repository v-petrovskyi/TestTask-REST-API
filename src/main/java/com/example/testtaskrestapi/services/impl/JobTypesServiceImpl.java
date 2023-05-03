package com.example.testtaskrestapi.services.impl;

import com.example.testtaskrestapi.entity.JobTypes;
import com.example.testtaskrestapi.repositories.JobTypesRepository;
import com.example.testtaskrestapi.services.JobTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobTypesServiceImpl implements JobTypesService {

    private final JobTypesRepository repository;

    @Autowired
    public JobTypesServiceImpl(JobTypesRepository repository) {
        this.repository = repository;
    }

    @Override
    public JobTypes add(JobTypes jobData) {
        return repository.save(jobData);
    }
}
