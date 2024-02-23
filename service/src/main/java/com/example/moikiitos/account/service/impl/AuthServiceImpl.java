package com.example.moikiitos.account.service.impl;

import com.example.moikiitos.account.model.Account;
import com.example.moikiitos.account.service.AuthService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final HttpSession httpSession;
    public static final String SESSION_NAME = "user";

    @Override
    public void login(Account user) {
        SecurityContextHolder.getContext().setAuthentication(createAuthentication(user));
        httpSession.setAttribute(SESSION_NAME, user.getName());
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
        return UsernamePasswordAuthenticationToken.authenticated(user.getName(),
                null, List.of());
    }
}
