package com.example.testtaskrestapi.services.impl;

import com.example.testtaskrestapi.entity.Tag;
import com.example.testtaskrestapi.repositories.TagRepository;
import com.example.testtaskrestapi.services.TagService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.BDDMockito.given;

class TagServiceImplTest {

    @Mock
    private TagRepository tagRepository;
    private final TagService tagService;

    TagServiceImplTest() {
        MockitoAnnotations.openMocks(this);
        this.tagService = new TagServiceImpl(tagRepository);
    }

    @Test
    void test_saveOrUpdate() {
        Tag expected = new Tag(1, "tag");
        given(tagRepository.save(expected)).willReturn(new Tag(1, "tag"));

        Tag actual = tagService.saveOrUpdate(expected);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void test_getByTag() {
        Optional<Tag> expected = Optional.of(new Tag(1, "tag"));
        given(tagRepository.findTagByTag("tag")).willReturn(Optional.of(new Tag(1, "tag")));
        Optional<Tag> actual = tagService.getByTag(new Tag(0, "tag"));

        Assertions.assertEquals(expected, actual);
    }
}