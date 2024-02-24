package com.example.moikiitos.shared.util;

import com.example.moikiitos.user.model.User;
import lombok.experimental.UtilityClass;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@UtilityClass
public class LoginContextUtils {

    public static void setLoginUser(User user) {
        var auth = UsernamePasswordAuthenticationToken.authenticated(user.getName(),
                null, List.of());
        auth.setDetails(user);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    public static String currentUserName() {
        var user = currentUser();
        if (user == null) {
            return null;
        }
        return user.getName();
    }

    public static User currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        return (User) authentication.getDetails();
    }
}
