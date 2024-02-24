package com.example.moikiitos.post.service.impl;

import com.example.moikiitos.post.model.Post;
import com.example.moikiitos.post.model.PostCreateDto;
import com.example.moikiitos.post.repository.PostRepository;
import com.example.moikiitos.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    @Mock
    PostRepository postRepository;

    PostServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new PostServiceImpl(postRepository);
    }

    @Test
    void createPost() {
        // CASE
        PostCreateDto createDto = new PostCreateDto();
        createDto.setContent("Hello world!");

        // WHEN
        service.createPost(new User(), createDto);

        // THEN
        verify(postRepository, Mockito.times(1)).add(any(Post.class));
    }
}