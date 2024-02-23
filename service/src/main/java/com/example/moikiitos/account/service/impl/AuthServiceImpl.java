package com.example.moikiitos.account.service.impl;

import com.example.moikiitos.account.model.Account;
import com.example.moikiitos.account.service.AuthService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public void login(Account user) {
        SecurityContextHolder.getContext().setAuthentication(createAuthentication(user));
    }

    @Override
    public String currentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        return authentication.getName();
    }

    private Authentication createAuthentication(Account user) {
        return new UsernamePasswordAuthenticationToken(user.getName(), user.getName());
    }
}
