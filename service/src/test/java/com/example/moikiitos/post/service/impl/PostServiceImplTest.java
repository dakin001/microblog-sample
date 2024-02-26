package com.example.moikiitos.post.service.impl;

import com.example.moikiitos.domain.post.service.impl.PostServiceImpl;
import com.example.moikiitos.domain.shared.mq.MqProducerService;
import com.example.moikiitos.domain.post.model.Post;
import com.example.moikiitos.domain.post.model.PostCreateDto;
import com.example.moikiitos.domain.post.repository.PostRepository;
import com.example.moikiitos.domain.user.model.User;
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
    @Mock
    MqProducerService mqService;

    PostServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new PostServiceImpl(postRepository, mqService);
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