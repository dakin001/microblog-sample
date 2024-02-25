package com.example.moikiitos.repository.mybatis;

import com.example.moikiitos.repository.mybatis.mapper.FollowerMapper;
import com.example.moikiitos.repository.mybatis.mapper.UserMapper;
import com.example.moikiitos.user.model.Follower;
import com.example.moikiitos.user.model.User;
import com.example.moikiitos.user.model.UserFollowQueryDto;
import com.example.moikiitos.user.repository.FollowerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FollowerRepositoryImpl implements FollowerRepository {
    private final FollowerMapper followerMapper;
    private final UserMapper userMapper;

    @Override
    public void add(Follower follower) {
        followerMapper.insert(follower);
    }

    @Override
    public void remove(Follower follower) {
        followerMapper.remove(follower);
    }

    @Override
    public boolean isExists(Follower follower) {
        return followerMapper.isExists(follower).orElse(false);
    }

    @Override
    public List<User> findFollowers(UserFollowQueryDto queryDto) {
        var user = userMapper.findByName(queryDto.getName());
        if (user == null) {
            return new ArrayList<>();
        }

        List<Long> ids = followerMapper.findFollowersByUserId(user.getId(), queryDto);
        if (ids.isEmpty()) {
            return new ArrayList<>();
        }
        return userMapper.findByIds(ids);
    }

    @Override
    public List<User> findFollowing(UserFollowQueryDto queryDto) {
        var user = userMapper.findByName(queryDto.getName());
        if (user == null) {
            return new ArrayList<>();
        }

        List<Long> ids = followerMapper.findFollowingByUserId(user.getId(), queryDto);
        if (ids.isEmpty()) {
            return new ArrayList<>();
        }
        return userMapper.findByIds(ids);
    }
}
