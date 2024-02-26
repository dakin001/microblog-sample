package com.example.moikiitos.domain.account.service;

import com.example.moikiitos.domain.account.model.AccountLoginDto;
import com.example.moikiitos.domain.account.model.AccountRegistrationDto;
import com.example.moikiitos.domain.user.model.User;

public interface AccountService {

    void register(AccountRegistrationDto registrationDto);

    User login(AccountLoginDto loginDto);

    // void logout();
}
