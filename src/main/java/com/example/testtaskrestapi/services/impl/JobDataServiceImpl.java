package com.example.testtaskrestapi.services.impl;

import com.example.testtaskrestapi.entity.JobData;
import com.example.testtaskrestapi.repositories.JobDataRepository;
import com.example.testtaskrestapi.services.JobDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class JobDataServiceImpl implements JobDataService {

    private final JobDataRepository repository;

    @Autowired
    public JobDataServiceImpl(JobDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public JobData add(JobData jobData) {
        return repository.save(jobData);
    }

    @Override
    public List<JobData> getAll() {
        return repository.findAll();
    }

    @Override
    public List<JobData> getPage(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable).getContent();
    }
}
