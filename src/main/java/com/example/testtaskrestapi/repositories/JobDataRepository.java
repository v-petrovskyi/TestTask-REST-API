package com.example.testtaskrestapi.repositories;

import com.example.testtaskrestapi.entity.JobData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobDataRepository extends JpaRepository<JobData, Long> {
    Optional<JobData> findBySlug(String slug);
}
