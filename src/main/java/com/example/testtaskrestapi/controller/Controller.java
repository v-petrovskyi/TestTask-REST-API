package com.example.testtaskrestapi.controller;

import com.example.testtaskrestapi.entity.JobData;
import com.example.testtaskrestapi.services.JobDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class Controller {

    private final JobDataService jobDataService;

    public Controller(JobDataService jobDataService) {
        this.jobDataService = jobDataService;
    }

    @GetMapping("/home")
    public List<JobData> returnPage(
            @RequestParam(name = "page", required = false) Optional<Integer> page,
            @RequestParam(name = "size", required = false) Optional<Integer> size,
            @RequestParam(name = "sort", required = false) Optional<String> sort) {
        return jobDataService.getPage(page.orElse(1), size.orElse(10), sort.orElse("id"));
    }
}
