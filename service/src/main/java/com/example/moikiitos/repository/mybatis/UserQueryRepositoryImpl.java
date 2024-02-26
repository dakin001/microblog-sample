package com.example.moikiitos.repository.mybatis;

import com.example.moikiitos.repository.mybatis.mapper.UserMapper;
import com.example.moikiitos.user.model.User;
import com.example.moikiitos.user.model.UserQueryDto;
import com.example.moikiitos.user.repository.UserQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserQueryRepositoryImpl implements UserQueryRepository {
    private final UserMapper userMapper;

    @Override
    public User findByNameOrEmail(UserQueryDto queryDto) {
        User user = userMapper.findByName(queryDto.getNameOrEmail());
        if (user != null) {
            return user;
        }
        return userMapper.findByEmail(queryDto.getNameOrEmail());
    }

    @Override
    public User findById(Long id) {
        return userMapper.findById(id);
    }
}
