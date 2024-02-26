package com.example.moikiitos.domain.account.repository;

import com.example.moikiitos.domain.account.model.Account;

public interface AccountRepository {
    void add(Account account);

    Account findByName(String name);

    Account findByEmail(String email);
}
