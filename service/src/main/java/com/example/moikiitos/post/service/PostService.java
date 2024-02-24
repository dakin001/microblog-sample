package com.example.moikiitos.post.service;

import com.example.moikiitos.post.model.Post;
import com.example.moikiitos.post.model.PostCreateDto;

public interface PostService {
    Post createPost(PostCreateDto reqDto);
}
