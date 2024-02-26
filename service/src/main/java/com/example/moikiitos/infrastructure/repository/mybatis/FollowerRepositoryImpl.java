package com.example.moikiitos.infrastructure.repository.mybatis;

import com.example.moikiitos.domain.shared.PageResult;
import com.example.moikiitos.domain.user.model.Follower;
import com.example.moikiitos.domain.user.model.User;
import com.example.moikiitos.domain.user.model.UserFollowQueryDto;
import com.example.moikiitos.domain.user.repository.FollowerRepository;
import com.example.moikiitos.infrastructure.repository.mybatis.mapper.FollowerMapper;
import com.example.moikiitos.infrastructure.repository.mybatis.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class FollowerRepositoryImpl implements FollowerRepository {
    private final FollowerMapper followerMapper;
    private final UserMapper userMapper;

    @Override
    public void add(Follower follower) {
        try {
            followerMapper.insert(follower);
        } catch (DuplicateKeyException ex) {
            log.info("follow records already exists.");
        }
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
    public PageResult<User> findFollowers(UserFollowQueryDto queryDto) {
        List<Long> ids = followerMapper.findFollowersByUserId(queryDto.getUserId(), queryDto);
        if (ids.isEmpty()) {
            return new PageResult<>();
        }
        var result = new PageResult<>(userMapper.findByIds(ids));
        // todo: query real total
        result.setTotal(result.getItems().size());
        return result;
    }

    @Override
    public PageResult<User> findFollowing(UserFollowQueryDto queryDto) {
        List<Long> ids = followerMapper.findFollowingByUserId(queryDto.getUserId(), queryDto);
        if (ids.isEmpty()) {
            return new PageResult<>();
        }
        var result = new PageResult<>(userMapper.findByIds(ids));
        // todo: query real total
        result.setTotal(result.getItems().size());
        return result;
    }
}
