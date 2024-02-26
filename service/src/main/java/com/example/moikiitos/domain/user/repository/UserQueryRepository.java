package com.example.moikiitos.domain.user.repository;

import com.example.moikiitos.domain.user.model.User;
import com.example.moikiitos.domain.user.model.UserQueryDto;

public interface UserQueryRepository {
    User findByNameOrEmail(UserQueryDto queryDto);

    User findById(Long id);
}
