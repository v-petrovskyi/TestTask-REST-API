package com.example.testtaskrestapi.repositories;

import com.example.testtaskrestapi.entity.JobTypes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobTypesRepository extends JpaRepository<JobTypes, String> {
}
