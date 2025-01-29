package com.project.rate_my_college.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.rate_my_college.course.model.Course;
import com.project.rate_my_college.course.service.CourseService;
import com.project.rate_my_college.util.JwtUtil;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;



@RestController
@RequestMapping("/api/courses")
public class CourseController {
    
    @Autowired
    private CourseService courseService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/add")
    public Course addCourse(@RequestHeader("Authorization") String authorizationHeader, 
                            @RequestBody Course course) {
        jwtUtil.validateToken(jwtUtil.extractToken(authorizationHeader)); // Validate token        
        return courseService.addCourse(course);
    }

    @GetMapping("/college/{collegeId}")
public List<Course> getCoursesByCollegeId(
        @PathVariable String collegeId,
        @RequestParam(required = false) String department,
        @RequestParam(required = false) String level) {

    return courseService.getCourses(collegeId, department, level);
}

    @DeleteMapping("/{id}")
    public void deleteCourse(@RequestHeader("Authorization") String authorizationHeader, 
                             @PathVariable String id) {
        jwtUtil.validateToken(jwtUtil.extractToken(authorizationHeader)); // Validate token
        courseService.deleteCourse(id);
    }

    
    
}
