package com.example.hello_again_admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hello_again_admin.model.ApiResponse;
import com.example.hello_again_admin.model.User;
import com.example.hello_again_admin.service.UserService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/setup")
public class SetupController {
    @GetMapping("/is-initialized")
    public ResponseEntity<ApiResponse<?>> isSystemInitialized() {
        UserService userService = new UserService();
        List<User> users = userService.getAllUsers();
        boolean isInitialized = users.size() > 0;
        if (isInitialized) {
            return ResponseEntity.ok(ApiResponse.success(HttpStatus.ACCEPTED.value(), "Initialized", null));
        } else {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.failure(HttpStatus.UNAUTHORIZED.value(),
                            "Portal is not initialized. Please create a super-admin"));
        }
    }

    @PostMapping("/create-super-admin")
    public String createSuperUser(@RequestBody String entity) {
        

        return entity;
    }

}