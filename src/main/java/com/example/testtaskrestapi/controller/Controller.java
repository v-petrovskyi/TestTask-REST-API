package com.example.testtaskrestapi.controller;

import com.example.testtaskrestapi.entity.JobData;
import com.example.testtaskrestapi.services.JobDataService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @GetMapping("/grouped")
    public ResponseEntity<List<GroupedResult>> getGroupedEntities() {
        List<Object[]> results = jobDataService.groupByLocation();
        List<GroupedResult> groupedResults = results.stream()
                .map(r -> new GroupedResult((String) r[0], (long) r[1]))
                .collect(Collectors.toList());
        return ResponseEntity.ok(groupedResults);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    private static class GroupedResult {
        private String location;
        private long count;

        public GroupedResult(String location, long count) {
            this.location = location;
            this.count = count;
        }
    }
}
