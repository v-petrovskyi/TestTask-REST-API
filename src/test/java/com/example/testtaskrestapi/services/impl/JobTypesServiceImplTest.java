package com.example.testtaskrestapi.services.impl;

import com.example.testtaskrestapi.entity.JobTypes;
import com.example.testtaskrestapi.entity.Tag;
import com.example.testtaskrestapi.repositories.JobTypesRepository;
import com.example.testtaskrestapi.services.JobTypesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class JobTypesServiceImplTest {
    @Mock
    private JobTypesRepository jobTypesRepository;
    private final JobTypesService jobTypesService;

    JobTypesServiceImplTest() {
        MockitoAnnotations.openMocks(this);
        this.jobTypesService= new JobTypesServiceImpl(jobTypesRepository);
    }

    @Test
    void test_saveOrUpdate() {
        JobTypes expected = new JobTypes(1,"some job");
        given(jobTypesRepository.save(expected)).willReturn(new JobTypes(1,"some job"));

        JobTypes actual = jobTypesService.saveOrUpdate(expected);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void test_getByJobTypes() {
        Optional<JobTypes> expected = Optional.of(new JobTypes(1,"some job"));
        given(jobTypesRepository.findByJobType("some job")).willReturn(Optional.of(new JobTypes(1,"some job")));
        Optional<JobTypes> actual = jobTypesService.getByJobTypes(new JobTypes(0,"some job"));

        Assertions.assertEquals(expected, actual);
    }
}