package com.example.testtaskrestapi.repositories;

import com.example.testtaskrestapi.entity.JobData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobDataRepository extends JpaRepository<JobData, String> {
}
