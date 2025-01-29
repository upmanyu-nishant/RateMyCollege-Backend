package com.project.rate_my_college.college.controller;

import com.project.rate_my_college.college.model.College;
import com.project.rate_my_college.college.service.CollegeService;
import com.project.rate_my_college.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/colleges") 
public class CollegeController {

    @Autowired
    private CollegeService collegeService;

    @Autowired
    private JwtUtil jwtUtil;

    // Public GET Endpoint: No Authorization Needed
    @GetMapping
    public List<College> getAllColleges() {
        return collegeService.getAllColleges();
    }

    // Public GET Endpoint: No Authorization Needed
    @GetMapping("/search")
    public List<College> searchColleges(@RequestParam(required = false) String name,
                                        @RequestParam(required = false) String location) {
        return collegeService.searchColleges(name, location);
    }

    // Public GET Endpoint: No Authorization Needed
    @GetMapping("/{id}")
    public College getCollegeById(@PathVariable String id) {
        return collegeService.getCollegeById(id);
    }

    // Secured POST Endpoint: Requires Authorization
    @PostMapping
    public College addCollege(@RequestHeader("Authorization") String authorizationHeader,
                              @RequestBody College college) {
        jwtUtil.validateToken(jwtUtil.extractToken(authorizationHeader)); // Validate token
        return collegeService.addCollege(college);
    }

    // Secured POST Endpoint for Batch Insert: Requires Authorization
    @PostMapping("/batch")
    public ResponseEntity<List<College>> addColleges(@RequestHeader("Authorization") String authorizationHeader,
                                                     @RequestBody List<College> colleges) {
        jwtUtil.validateToken(jwtUtil.extractToken(authorizationHeader)); // Validate token
        List<College> addedColleges = collegeService.addColleges(colleges);
        return ResponseEntity.ok(addedColleges);  // Return the list of added colleges
    }

    // Secured DELETE Endpoint: Requires Authorization
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCollege(@RequestHeader("Authorization") String authorizationHeader,
                                                @PathVariable String id) {
        jwtUtil.validateToken(jwtUtil.extractToken(authorizationHeader)); // Validate token
        boolean deleted = collegeService.deleteCollege(id);
        if (deleted) {
            return ResponseEntity.ok("College with ID " + id + " has been deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("College with ID " + id + " not found.");
        }
    }

    // Secured PATCH Endpoint: Requires Authorization
    @PatchMapping("/{id}")
    public ResponseEntity<College> updateCollege(@RequestHeader("Authorization") String authorizationHeader,
                                                 @PathVariable String id,
                                                 @RequestBody Map<String, Object> updates) {
        jwtUtil.validateToken(jwtUtil.extractToken(authorizationHeader)); // Validate token
        College updatedCollege = collegeService.updateCollege(id, updates);
        if (updatedCollege != null) {
            return ResponseEntity.ok(updatedCollege);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }
}
