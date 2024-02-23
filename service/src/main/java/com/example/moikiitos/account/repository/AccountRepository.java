package com.example.moikiitos.account.repository;

import com.example.moikiitos.account.model.Account;

public interface AccountRepository {
    void add(Account account);

    Account findByName(String name);

    Account findByEmail(String email);
}
