package com.example.moikiitos.domain.shared.util;

import com.example.moikiitos.domain.user.model.User;
import lombok.experimental.UtilityClass;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@UtilityClass
public class LoginContextUtils {
    public static final String SESSION_USER = "user";

    public static void setLoginUser(User user) {
        var auth = UsernamePasswordAuthenticationToken.authenticated(user.getName(),
                null, List.of());
        auth.setDetails(user);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    public static User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        return (User) authentication.getDetails();
    }
}
