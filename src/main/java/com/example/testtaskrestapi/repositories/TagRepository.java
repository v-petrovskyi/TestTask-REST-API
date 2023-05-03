package com.example.testtaskrestapi.repositories;

import com.example.testtaskrestapi.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,String> {
}
