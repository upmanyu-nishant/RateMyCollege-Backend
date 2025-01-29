package com.project.rate_my_college.college.controller;

import com.project.rate_my_college.college.model.CollegeRatingCard;
import com.project.rate_my_college.college.service.CollegeRatingCardService;
import com.project.rate_my_college.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ratingCards")
public class CollegeRatingCardController {

    @Autowired
    private CollegeRatingCardService ratingCardService;

    @Autowired
    private JwtUtil jwtUtil;

    // Public GET Endpoint: No Authorization Needed
    @GetMapping("/college/{collegeId}")
    public List<CollegeRatingCard> getRatingsByCollegeId(@PathVariable String collegeId) {
        return ratingCardService.getRatingsByCollegeId(collegeId);
    }

    // Public GET Endpoint: No Authorization Needed
    @GetMapping("/email/{emailId}")
    public List<CollegeRatingCard> getRatingsByEmailId(@PathVariable String emailId) {
        return ratingCardService.getRatingsByEmailId(emailId);
    }

    // Secured POST Endpoint: Requires Authorization
    @PostMapping
    public ResponseEntity<CollegeRatingCard> addRatingCard(@RequestHeader("Authorization") String authorizationHeader,
                                                    @RequestBody CollegeRatingCard ratingCard) {
        String token = jwtUtil.extractToken(authorizationHeader); // Extract token
        String email = jwtUtil.extractEmail(token); // Validate token and extract email
        ratingCard.setEmailId(email); // Attach email from token to the rating
        CollegeRatingCard savedRatingCard = ratingCardService.addRatingCard(ratingCard);
        return ResponseEntity.ok(savedRatingCard);
    }

    // Secured DELETE Endpoint: Requires Authorization
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRatingCard(@RequestHeader("Authorization") String authorizationHeader,
                                                   @PathVariable String id) {
        String token = jwtUtil.extractToken(authorizationHeader); // Extract token
        jwtUtil.validateToken(token); // Validate token
        boolean deleted = ratingCardService.deleteRatingCard(id);
        if (deleted) {
            return ResponseEntity.ok("Rating card with ID " + id + " has been deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("Rating card with ID " + id + " not found.");
        }
    }

    // Secured PATCH Endpoint: Requires Authorization
    @PatchMapping("/{id}")
    public ResponseEntity<CollegeRatingCard> updateRatingCard(@RequestHeader("Authorization") String authorizationHeader,
                                                       @PathVariable String id,
                                                       @RequestBody Map<String, Object> updates) {
        String token = jwtUtil.extractToken(authorizationHeader); // Extract token
        jwtUtil.validateToken(token); // Validate token
        CollegeRatingCard updatedRatingCard = ratingCardService.updateRatingCard(id, updates);
        if (updatedRatingCard != null) {
            return ResponseEntity.ok(updatedRatingCard);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }
}
