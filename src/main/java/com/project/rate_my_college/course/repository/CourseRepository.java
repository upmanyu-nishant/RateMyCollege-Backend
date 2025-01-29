package com.project.rate_my_college.course.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.project.rate_my_college.course.model.Course;

public interface CourseRepository extends MongoRepository<Course, String> {
    Optional<Course> findById(String id);

    // Fetch courses by College ID
    List<Course> findByCollegeId(String collegeId);

    // Fetch courses by College ID and Department
    List<Course> findByCollegeIdAndDepartment(String collegeId, String department);

    void deleteById(String id);

    // Fetch courses by College ID and Level
    List<Course> findByCollegeIdAndLevel(String collegeId, String level);

    // Fetch courses by College ID, Department, and Level
    List<Course> findByCollegeIdAndDepartmentAndLevel(String collegeId, String department, String level);
}
