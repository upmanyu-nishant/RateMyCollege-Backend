package com.project.rate_my_college.course.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.rate_my_college.course.model.Course;
import com.project.rate_my_college.course.repository.CourseRepository;
import com.project.rate_my_college.service.SequenceGeneratorService;

@Service
public class CourseService {
    
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    public Course addCourse(Course course) {
        long id = sequenceGeneratorService.getNextSequenceId("courses");  // Generate auto-increment ID
        course.setId(String.valueOf(id));  // Set the numeric ID
        return courseRepository.save(course);
    }

    public List<Course> getCourses(String collegeId, String department, String level) {
        if (department != null && !department.isEmpty() && level != null && !level.isEmpty()) {
            // Filter by College ID, Department, and Level
            return courseRepository.findByCollegeIdAndDepartmentAndLevel(collegeId, department, level);
        } else if (department != null && !department.isEmpty()) {
            // Filter by College ID and Department
            return courseRepository.findByCollegeIdAndDepartment(collegeId, department);
        } else if (level != null && !level.isEmpty()) {
            // Filter by College ID and Level
            return courseRepository.findByCollegeIdAndLevel(collegeId, level);
        } else {
            // Fetch all courses for the College
            return courseRepository.findByCollegeId(collegeId);
        }
    }
    public void deleteCourse(String id) {
        courseRepository.deleteById(id);
    }
}
