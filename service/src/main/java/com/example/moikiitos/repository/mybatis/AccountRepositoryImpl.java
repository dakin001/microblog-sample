package com.example.moikiitos.repository.mybatis;

import com.example.moikiitos.account.model.Account;
import com.example.moikiitos.account.repository.AccountRepository;
import com.example.moikiitos.repository.mybatis.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class AccountRepositoryImpl implements AccountRepository {
    private final AccountMapper userMapper;

    @Override
    public void add(Account account) {
        try {
            userMapper.insert(account);
        } catch (DuplicateKeyException ex) {
            log.info("account already exists.");
        }
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
