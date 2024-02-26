package com.example.moikiitos.domain.post.service;

import com.example.moikiitos.domain.post.model.PostCreateDto;
import com.example.moikiitos.domain.post.model.Post;
import com.example.moikiitos.domain.user.model.User;

public interface PostService {
    Post createPost(User user, PostCreateDto reqDto);
}
