package com.example.moikiitos.account.service.impl;

import com.example.moikiitos.account.model.Account;
import com.example.moikiitos.account.service.AuthService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthServiceImpl implements AuthService {

    @Override
    public void login(Account user) {
        SecurityContextHolder.getContext().setAuthentication(createAuthentication(user));
    }

    private Authentication createAuthentication(Account user) {
        return new UsernamePasswordAuthenticationToken(user.getName(), user.getName());
    }
}
