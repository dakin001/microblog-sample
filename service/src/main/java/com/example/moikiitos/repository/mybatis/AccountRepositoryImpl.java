package com.example.moikiitos.repository.mybatis;

import com.example.moikiitos.account.model.Account;
import com.example.moikiitos.account.repository.AccountRepository;
import com.example.moikiitos.repository.mybatis.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {
    private final UserMapper userMapper;

    @Override
    public void add(Account account) {
        userMapper.insert(account);
    }

    @Override
    public Account findByName(String name) {
        return userMapper.findByName(name);
    }

    @Override
    public Account findByEmail(String email) {
        return userMapper.findByEmail(email);
    }
}
