package com.example.moikiitos.post.service;

import com.example.moikiitos.post.model.Post;
import com.example.moikiitos.post.model.PostCreateDto;
import com.example.moikiitos.user.model.User;

public interface PostService {
    Post createPost(User user, PostCreateDto reqDto);
}
