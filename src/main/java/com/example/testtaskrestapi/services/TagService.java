package com.example.testtaskrestapi.services;

import com.example.testtaskrestapi.entity.Tag;

import java.util.Optional;

public interface TagService {
    Tag saveOrUpdate(Tag tag);
    Optional<Tag> getByTag(Tag tag);
}
