package com.example.testtaskrestapi.services.impl;

import com.example.testtaskrestapi.entity.Tag;
import com.example.testtaskrestapi.repositories.TagRepository;
import com.example.testtaskrestapi.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository repository;

    @Autowired
    public TagServiceImpl(TagRepository repository) {
        this.repository = repository;
    }

    @Override
    public Tag saveOrUpdate(Tag tag) {
        return getByTag(tag).orElseGet(()->repository.save(tag));
    }

    @Override
    public Optional<Tag> getByTag(Tag tag) {
        return repository.findTagByTag(tag.getTag());
    }
}
