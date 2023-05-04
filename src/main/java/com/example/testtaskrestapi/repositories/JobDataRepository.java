package com.example.testtaskrestapi.repositories;

import com.example.testtaskrestapi.entity.JobData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JobDataRepository extends JpaRepository<JobData, Long> {
    Optional<JobData> findBySlug(String slug);
    @Query("SELECT location, COUNT(location) as count FROM JobData GROUP BY location")
    List<Object[]>groupByLocation();
}
