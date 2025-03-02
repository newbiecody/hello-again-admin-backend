package com.example.hello_again_admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hello_again_admin.util.JwtUtils;

@Service
public class JwtService {

    @Autowired
    private JwtUtils jwtUtils;

    public String createAuthToken(String username) {
        return jwtUtils.generateToken(username);
    }

    public boolean isValid(String token, String username) {
        return jwtUtils.validateToken(token, username);
    }
}
