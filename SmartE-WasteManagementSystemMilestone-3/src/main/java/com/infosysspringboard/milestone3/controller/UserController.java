package com.infosysspringboard.milestone3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.infosysspringboard.milestone3.dto.LoginRequest;
import com.infosysspringboard.milestone3.entity.User;
import com.infosysspringboard.milestone3.security.JWTService;
import com.infosysspringboard.milestone3.security.JwtUtil;
import com.infosysspringboard.milestone3.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JWTService jwtService;

    //REGISTER
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return userService.save(user);
    }

    //LOGIN
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        Authentication auth = manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserEmail(), request.getPassword()
                )
        );
        if (auth.isAuthenticated()) {
            return jwtService.generateToken(request.getUserEmail());
        }
        return "login failed";
    }

    //GET LOGGED-IN USER PROFILE
    @GetMapping("/me")
    public Object getMyProfile() {
        String email = JwtUtil.getLoggedInUserEmail();
        User user = userService.getUserByEmail(email);
        return user;
    }

}
