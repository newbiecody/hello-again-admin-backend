package com.example.hello_again_admin.controller.admin;

import com.example.hello_again_admin.model.User;
import com.example.hello_again_admin.repository.UserRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/admin")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/allUsers")
    public Iterable<User> getMethodName() {
        return this.userRepository.findAll();
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody String entity) {
        // TODO: process POST request

        return entity;
    }
}