package com.example.moikiitos.account.service;

import com.example.moikiitos.account.model.AccountLoginDto;
import com.example.moikiitos.account.model.AccountRegistrationDto;

public interface AccountService {

    void register(AccountRegistrationDto registrationDto);

    boolean login(AccountLoginDto loginDto);

    // void logout();
}
