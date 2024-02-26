package com.example.moikiitos.infrastructure.config;

import com.example.moikiitos.domain.shared.util.LoginContextUtils;
import com.example.moikiitos.domain.user.model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.example.moikiitos.domain.shared.util.LoginContextUtils.SESSION_USER;

@Component
public class SessionAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute(SESSION_USER);
        if (user != null) {
            LoginContextUtils.setLoginUser(user);
        }

        filterChain.doFilter(request, response);
    }
}
