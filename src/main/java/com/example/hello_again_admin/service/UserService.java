package com.example.hello_again_admin.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hello_again_admin.model.Role;
import com.example.hello_again_admin.model.User;
import com.example.hello_again_admin.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    // public UserService(UserRepository userRepository) {
    // this.userRepository = userRepository;
    // }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    public Set<Role> getRoles(Long id) {
        return getUserById(id).map(User::getRoles).orElseGet(HashSet::new);
    }

    public void register(String username, String rawPassword) {
        User user = new User(null, username, username, rawPassword);
        userRepository.save(user);
    }

    public boolean authenticate(String username, String rawPassword) {
        Optional<User> user = getUserByUsername(username);
        return user.isPresent() && user.get().checkPassword(rawPassword);
    }
}