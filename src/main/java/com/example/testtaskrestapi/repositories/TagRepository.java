package com.example.testtaskrestapi.repositories;

import com.example.testtaskrestapi.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag,Long> {
    Optional<Tag> findTagByTag(String tag);
}
