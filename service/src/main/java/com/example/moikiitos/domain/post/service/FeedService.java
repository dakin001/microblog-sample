package com.example.moikiitos.domain.post.service;

import com.example.moikiitos.domain.post.model.FeedQueryDto;
import com.example.moikiitos.domain.post.model.Post;

import java.util.List;

public interface FeedService {
    List<Post> queryUserFeed(FeedQueryDto reqDto);

    void addPostIntoFeed(Post post);

    void reGenerateFeed(Long userId);
}
