package com.example.testtaskrestapi.services;

import com.example.testtaskrestapi.entity.Tag;

import java.util.Optional;

public interface TagService {
    Tag add(Tag tag);
    Optional<Tag> getByTag(Tag tag);
}
