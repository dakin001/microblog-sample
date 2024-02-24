package com.example.moikiitos.account.service;

import com.example.moikiitos.account.model.AccountLoginDto;
import com.example.moikiitos.account.model.AccountRegistrationDto;
import com.example.moikiitos.user.model.User;

public interface AccountService {

    void register(AccountRegistrationDto registrationDto);

    User login(AccountLoginDto loginDto);

    // void logout();
}
