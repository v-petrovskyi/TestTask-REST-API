package com.example.testtaskrestapi.services.impl;

import com.example.testtaskrestapi.entity.JobData;
import com.example.testtaskrestapi.repositories.JobDataRepository;
import com.example.testtaskrestapi.services.JobDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<JobData> getPage(int page, int size, String input) {
        String[] strings = input.split(",");
        Sort sort;
        try {
            sort = Sort.by(Sort.Direction.fromString(strings[1]), strings[0]);
        } catch (IndexOutOfBoundsException e) {
            sort = Sort.by(Sort.Direction.ASC, strings[0]);
        }
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        return repository.findAll(pageable).getContent();
    }

    @Override
    public Optional<JobData> findBySlug(String slug) {
        return repository.findBySlug(slug);
    }
}
