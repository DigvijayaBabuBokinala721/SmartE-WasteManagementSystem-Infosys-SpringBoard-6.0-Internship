package com.infosysspringboard.milestone3.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class JwtUtil {

    public static String getLoggedInUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
