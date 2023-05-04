package com.example.testtaskrestapi.services.impl;

import com.example.testtaskrestapi.entity.JobData;
import com.example.testtaskrestapi.entity.JobTypes;
import com.example.testtaskrestapi.entity.Tag;
import com.example.testtaskrestapi.repositories.JobDataRepository;
import com.example.testtaskrestapi.services.JobDataService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class JobDataServiceImplTest {
    @Mock
    private JobDataRepository jobDataRepository;
    private final JobDataService jobDataService;

    JobDataServiceImplTest(){
        MockitoAnnotations.openMocks(this);
        this.jobDataService = new JobDataServiceImpl(jobDataRepository);
    }

    @Test
    void test_add() {
        JobData expected = new JobData(1, "junior-pmo-automotive-all-gender-wolfsburg-187433",
                "ALTEN",
                "Junior PMO Automotive (all gender)",
                "desc",
                true,
                "some url",
                Arrays.asList(new Tag(1, "tag"), new Tag(2, "tag2")),
                Arrays.asList(new JobTypes(1, "type1"), new JobTypes(2, "type2")),
                "location",
                LocalDateTime.now()
        );

        given(jobDataRepository.save(expected)).willReturn(expected);

        JobData actual = jobDataService.add(expected);
        Assertions.assertEquals(expected, actual);
    }


    @Test
    void test_findBySlug() {
        Optional<JobData> expected = Optional.of(new JobData(1, "junior-pmo-automotive-all-gender-wolfsburg-187433",
                "ALTEN",
                "Junior PMO Automotive (all gender)",
                "desc",
                true,
                "some url",
                Arrays.asList(new Tag(1, "tag"), new Tag(2, "tag2")),
                Arrays.asList(new JobTypes(1, "type1"), new JobTypes(2, "type2")),
                "location",
                LocalDateTime.now()
        ));

        given(jobDataRepository.findBySlug("junior-pmo-automotive-all-gender-wolfsburg-187433")).willReturn(expected);

        Optional<JobData> actual = jobDataService.findBySlug("junior-pmo-automotive-all-gender-wolfsburg-187433");

        Assertions.assertEquals(expected, actual);
    }

}