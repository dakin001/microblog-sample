package com.example.moikiitos.config;

import com.example.moikiitos.account.model.Account;
import com.example.moikiitos.account.service.AuthService;
import com.example.moikiitos.account.service.impl.AuthServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SessionAuthenticationFilter extends OncePerRequestFilter {
    private final AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String userName = (String) request.getSession().getAttribute(AuthServiceImpl.SESSION_NAME);
        if (userName != null) {
            Account account = new Account();
            account.setName(userName);

            authService.login(account);
        }

        filterChain.doFilter(request, response);
    }
}
