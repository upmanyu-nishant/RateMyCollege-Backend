package com.project.rate_my_college.service;

import com.project.rate_my_college.model.User;
import com.project.rate_my_college.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User registerUser(User user) {
        // Encrypt password if present
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if(user.getGoogleSubId() != null && !user.getGoogleSubId().isEmpty()) {
            user.setGoogleSubId(passwordEncoder.encode(user.getGoogleSubId()));
        }
        return userRepository.save(user);
    }

    public boolean validatePassword(User user, String rawPassword) {
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }
    public boolean validateGoogleSubId(User user, String googleSubId) {
        return passwordEncoder.matches(googleSubId, user.getGoogleSubId());
    }
}
