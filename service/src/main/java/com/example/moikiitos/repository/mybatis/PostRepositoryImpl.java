package com.example.moikiitos.repository.mybatis;

import com.example.moikiitos.post.model.Post;
import com.example.moikiitos.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    @Override
    public void add(Post post) {

    }
}
