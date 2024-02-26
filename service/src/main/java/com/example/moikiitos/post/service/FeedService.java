package com.example.moikiitos.post.service;

import com.example.moikiitos.post.model.FeedQueryDto;
import com.example.moikiitos.post.model.Post;

import java.util.List;

public interface FeedService {
    List<Post> queryUserFeed(FeedQueryDto reqDto);

    void addPostIntoFeed(Post post);

    void reGenerateFeed(Long userId);
}
