package com.project.rate_my_college.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.rate_my_college.course.model.CourseRating;
import com.project.rate_my_college.course.service.CourseRatingService;
import com.project.rate_my_college.util.JwtUtil;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;



@RestController
@RequestMapping("/api/courseRatings")
public class CourseRatingController {
    
    @Autowired
    private CourseRatingService courseRatingService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/course/{courseId}")
    public List<CourseRating> getRatingsByCourseId(@PathVariable String courseId) {
        return courseRatingService.getRatingsByCourseId(courseId);
    }

    @GetMapping("/email/{emailId}")
    public List<CourseRating> getRatingsByEmailId(@PathVariable String emailId) {
        return courseRatingService.getCourseRatingByEmailId(emailId);
    }

    @PostMapping("/add")
    public CourseRating addCourseRating(@RequestHeader("Authorization") String authorizationHeader,
                                                        @RequestBody CourseRating courseRating) {
            System.out.println("header: " + authorizationHeader);
            System.out.println("token: " + jwtUtil.extractToken(authorizationHeader));
            jwtUtil.validateToken(jwtUtil.extractToken(authorizationHeader));
            CourseRating savedCourseRating = courseRatingService.addRating(courseRating);
        return savedCourseRating;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourseRating(@RequestHeader("Authorization") String authorizationHeader,
                                                    @PathVariable String id) {
        String token = jwtUtil.extractToken(authorizationHeader); // Extract token
        jwtUtil.validateToken(token); // Validate token
        boolean deleted = courseRatingService.deleteRating(id);
        if (deleted) {
            return ResponseEntity.ok("Course rating with ID " + id + " has been deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("Course rating with ID " + id + " not found.");
        }
    }
    

 
    
}
