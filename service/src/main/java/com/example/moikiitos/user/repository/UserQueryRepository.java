package com.example.moikiitos.user.repository;

import com.example.moikiitos.user.model.User;
import com.example.moikiitos.user.model.UserQueryDto;

public interface UserQueryRepository {
    User findByNameOrEmail(UserQueryDto queryDto);

    User findByName(String name);
}
