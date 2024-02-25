package com.example.moikiitos.post.repository;

import com.example.moikiitos.post.model.Post;
import com.example.moikiitos.shared.PageQuery;

import java.util.List;

public interface PostRepository {
    void add(Post post);

    List<Post> queryFeed(Long userId, PageQuery page);
}
