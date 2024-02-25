package com.example.moikiitos.repository.mybatis;

import com.example.moikiitos.post.model.Post;
import com.example.moikiitos.post.repository.PostRepository;
import com.example.moikiitos.repository.mybatis.mapper.PostMapper;
import com.example.moikiitos.repository.mybatis.mapper.UserMapper;
import com.example.moikiitos.shared.PageQuery;
import com.example.moikiitos.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final PostMapper postMapper;
    private final UserMapper userMapper;

    @Override
    public void add(Post post) {
        postMapper.insert(post);
    }

    @Override
    public List<Post> findFeedByUserId(Long userId, PageQuery page) {
        var posts = postMapper.findFeedByUserId(userId, page);
        setUserInfo(posts);
        return posts;
    }

    private void setUserInfo(List<Post> posts) {
        var userIds = posts.stream().map(x -> x.getUser().getId()).collect(Collectors.toList());
        if (userIds.isEmpty()) {
            return;
        }
        Map<Long, User> idToUser = userMapper.findByIds(userIds)
                .stream().collect(Collectors.toMap(User::getId, Function.identity()));
        posts.forEach(x -> x.setUser(idToUser.get(x.getUser().getId())));
    }
}
