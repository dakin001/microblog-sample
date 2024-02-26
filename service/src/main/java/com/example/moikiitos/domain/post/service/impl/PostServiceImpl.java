package com.example.moikiitos.domain.post.service.impl;

import com.example.moikiitos.domain.post.model.Post;
import com.example.moikiitos.domain.post.model.PostCreateDto;
import com.example.moikiitos.domain.post.repository.PostRepository;
import com.example.moikiitos.domain.post.service.PostService;
import com.example.moikiitos.domain.shared.mq.MqProducerService;
import com.example.moikiitos.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository repository;
    private final MqProducerService mqService;

    @Override
    public Post createPost(User user, PostCreateDto reqDto) {
        Post post = new Post();
        post.setUser(user);
        post.setContent(reqDto.getContent());
        repository.add(post);

        postCreated(post);
        return post;
    }

    private void postCreated(Post post) {
        mqService.sendPostCreatedMsg(post);
    }
}
