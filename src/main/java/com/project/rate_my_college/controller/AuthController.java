package com.project.rate_my_college.controller;

import com.project.rate_my_college.model.User;
import com.project.rate_my_college.service.UserService;
import com.project.rate_my_college.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Merged API for register and login
     */
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody User user) {
        // Validate that either password or Google Sub ID is provided
        if ((user.getPassword() == null || user.getPassword().isEmpty()) &&
            (user.getGoogleSubId() == null || user.getGoogleSubId().isEmpty())) {
            return ResponseEntity.badRequest().body("Either password or Google Sub ID must be provided.");
        }

        // Check if the user exists
        Optional<User> existingUserOpt = userService.getUserByEmail(user.getEmail());

        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();

            // Validate login credentials
            boolean isValidUser = (user.getGoogleSubId() != null && userService.validateGoogleSubId(existingUser,user.getGoogleSubId())) ||
                                  (user.getPassword() != null && userService.validatePassword(existingUser, user.getPassword()));

            if (!isValidUser) {
                return ResponseEntity.status(401).body(Map.of("message", "Invalid credentials."));
            }

            // Generate JWT token
            String token = jwtUtil.generateToken(existingUser.getEmail());
            return ResponseEntity.ok(Map.of(
                    "message", "Login successful.",
                    "token", token
            ));
        } else {

            if (user.getFirstName() == null || user.getFirstName().isEmpty() ||
                user.getLastName() == null || user.getLastName().isEmpty() ||
                 user.getEmail() == null || user.getEmail().isEmpty()) {
                return ResponseEntity.badRequest().body("Incomplete information. First name and last name are required.");
            }
            // If the user does not exist, register the user
            User newUser = new User();
            newUser.setEmail(user.getEmail());
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());

            // Set password or Google Sub ID based on the provided credentials
            if (user.getGoogleSubId() != null) {
                newUser.setGoogleSubId(user.getGoogleSubId());
            } else if (user.getPassword() != null) {
                newUser.setPassword(user.getPassword());
            }

            userService.registerUser(newUser);

            // Generate JWT token
            String token = jwtUtil.generateToken(newUser.getEmail());
            return ResponseEntity.ok(Map.of(
                    "message", "Registration successful.",
                    "token", token
            ));
        }
    }
}
