package com.example.testtaskrestapi.repositories;

import com.example.testtaskrestapi.entity.JobTypes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobTypesRepository extends JpaRepository<JobTypes, String> {
    Optional<JobTypes> findByJobType(String jobType);
}
