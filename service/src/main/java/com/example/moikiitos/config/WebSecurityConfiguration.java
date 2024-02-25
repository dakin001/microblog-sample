package com.example.moikiitos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfiguration {
    @Value("${permitAllUrls:}")
    private String[] permitAllUrls;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, SessionAuthenticationFilter filter) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                        .requestMatchers(permitAllUrls).permitAll()
                        .anyRequest().authenticated()
                ).addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .csrf(x -> x.disable());

        return http.build();
    }
}
