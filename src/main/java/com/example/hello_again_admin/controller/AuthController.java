package com.example.hello_again_admin.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.hello_again_admin.model.LoginRequest;
import com.example.hello_again_admin.service.JwtService;
import com.example.hello_again_admin.service.UserService;

@RestController
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    public AuthController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest entity) {
        boolean isAuthenticated = userService.authenticate(entity.getUsername(), entity.getPassword());
        if (isAuthenticated) {
            return jwtService.createAuthToken(entity.getUsername());
        }
        return null;
    }
}
