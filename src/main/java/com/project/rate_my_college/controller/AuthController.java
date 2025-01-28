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

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody User user) {
        // Validate user input
        ResponseEntity<?> validationResponse = validateUserInput(user);
        if (validationResponse != null) {
            return validationResponse; // Return error response if validation fails
        }
    
        // Check if the user exists
        Optional<User> existingUserOpt = userService.getUserByEmail(user.getEmail());
        if (existingUserOpt.isPresent()) {
            // Log in the user if they already exist
            return loginUser(existingUserOpt.get(), user);
        } else {
            // Register the user and log them in if they don't exist
            return registerAndLoginUser(user);
        }
    }


    @PostMapping("/refresh")
    public ResponseEntity<?> refreshAccessToken(@RequestBody Map<String, String> request) {
        String refreshToken = extractRefreshToken(request);

        if (!isRefreshTokenValid(refreshToken)) {
            return unauthorizedResponse("Invalid or expired refresh token.");
        }

        String email = extractEmailFromRefreshToken(refreshToken);

        if (!isRefreshTokenAuthorized(email, refreshToken)) {
            return unauthorizedResponse("Unauthorized refresh token.");
        }

        Map<String, String> newTokens = generateNewTokens(email);

        return ResponseEntity.ok(newTokens);
    }

    /**
     * Extracts the refresh token from the request body.
     */
    private String extractRefreshToken(Map<String, String> request) {
        return request.getOrDefault("refreshToken", "");
    }

    /**
     * Validates if the provided refresh token is valid and not expired.
     */
    private boolean isRefreshTokenValid(String refreshToken) {
        return refreshToken != null && !refreshToken.isEmpty() && jwtUtil.validateToken(refreshToken);
    }

    /**
     * Extracts the email from the refresh token.
     */
    private String extractEmailFromRefreshToken(String refreshToken) {
        return jwtUtil.extractEmail(refreshToken);
    }

    /**
     * Checks if the refresh token is authorized for the user.
     * This function can query the database or a revocation list if implemented.
     */
    private boolean isRefreshTokenAuthorized(String email, String refreshToken) {
        // Optional: Implement a database check for refresh token validity
        // For example:
        // return refreshTokenService.isValidRefreshToken(email, refreshToken);
        return email != null && !email.isEmpty();
    }

    /**
     * Generates new access and refresh tokens for the user.
     */
    private Map<String, String> generateNewTokens(String email) {
        String newAccessToken = jwtUtil.generateToken(email);
        String newRefreshToken = jwtUtil.generateRefreshToken(email); // Optional: Implement refresh token rotation

        return Map.of(
            "accessToken", newAccessToken,
            "refreshToken", newRefreshToken
        );
    }

    /**
     * Returns a 401 Unauthorized response with a custom message.
     */
    private ResponseEntity<Map<String, String>> unauthorizedResponse(String message) {
        return ResponseEntity.status(401).body(Map.of("message", message));
    }
    

    private ResponseEntity<?> validateUserInput(User user) {
        // Ensure either password or Google Sub ID is provided
        if ((user.getPassword() == null || user.getPassword().isEmpty()) &&
            (user.getGoogleSubId() == null || user.getGoogleSubId().isEmpty())) {
            return ResponseEntity.badRequest().body("Either password or Google Sub ID must be provided.");
        }
        Optional<User> existingUserOpt = userService.getUserByEmail(user.getEmail());
        // Ensure first name, last name, and email are provided for registration
        if ((!existingUserOpt.isPresent())&&((user.getFirstName() == null || user.getFirstName().isEmpty()) ||
            (user.getLastName() == null || user.getLastName().isEmpty()) ||
            (user.getEmail() == null || user.getEmail().isEmpty()))) {
            return ResponseEntity.badRequest().body("Incomplete information. First name, last name, and email are required.");
        }
        

        return null; // Input is valid
    }


// Helper method to log in an existing user
private ResponseEntity<?> loginUser(User existingUser, User user) {
    boolean isValidUser = (user.getGoogleSubId() != null && userService.validateGoogleSubId(existingUser, user.getGoogleSubId())) ||
                          (user.getPassword() != null && userService.validatePassword(existingUser, user.getPassword()));

    if (!isValidUser) {
        return ResponseEntity.status(401).body(Map.of("message", "Invalid credentials."));
    }

    // Generate access and refresh tokens
    String accessToken = jwtUtil.generateToken(existingUser.getEmail());
    String refreshToken = jwtUtil.generateRefreshToken(existingUser.getEmail());
    return ResponseEntity.ok(Map.of(
            "message", "Login successful.",
            "accessToken", accessToken,
            "refreshToken", refreshToken
    ));
}

// Helper method to register and log in a new user
private ResponseEntity<?> registerAndLoginUser(User user) {
    // Create a new user
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

    // Save the new user
    userService.registerUser(newUser);

    // Generate access and refresh tokens
    String accessToken = jwtUtil.generateToken(newUser.getEmail());
    String refreshToken = jwtUtil.generateRefreshToken(newUser.getEmail());

    return ResponseEntity.ok(Map.of(
            "message", "Registration successful.",
            "accessToken", accessToken,
            "refreshToken", refreshToken
    ));
}

}
