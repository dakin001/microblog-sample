package com.example.moikiitos.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = false)
public class WebSecurityConfiguration {
    @Value("${permitAllUrls:}")
    private String[] permitAllUrls;

    @Value("${securityDisEnable:false}")
    private Boolean securityDisEnable;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, SessionAuthenticationFilter filter) throws Exception {
        http.authorizeHttpRequests(requests -> {
                            if (securityDisEnable) {
                                requests.anyRequest().permitAll();
                                return;
                            }
                            requests.requestMatchers(permitAllUrls).permitAll()
                                    .anyRequest().authenticated();
                        }
                ).addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .csrf(x -> x.disable()).cors();

        return http.build();
    }
}
