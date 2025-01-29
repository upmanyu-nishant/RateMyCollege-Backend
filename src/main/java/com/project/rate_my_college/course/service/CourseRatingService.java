package com.project.rate_my_college.course.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.rate_my_college.course.model.Course;
import com.project.rate_my_college.course.model.CourseRating;
import com.project.rate_my_college.course.repository.CourseRatingRepository;
import com.project.rate_my_college.course.repository.CourseRepository;


@Service
public class CourseRatingService {

    @Autowired
    private CourseRatingRepository courseRatingRepository;
    
    @Autowired
    private CourseRepository courseRepository;

    public List<CourseRating> getRatingsByCourseId(String courseId) {
        return courseRatingRepository.findByCourseId(courseId);
    }
    
    public CourseRating addRating(CourseRating courseRating) {
        courseRating.setOverallRating(calculateOverallRating(courseRating));
        updateCourseRatings(courseRating.getCourseId());
        return courseRatingRepository.save(courseRating);
    }

    public boolean deleteRating(String id) {
        Optional<CourseRating> optionalRating = courseRatingRepository.findById(id);
        if (optionalRating.isPresent()) {
            CourseRating rating = optionalRating.get();
            courseRatingRepository.deleteById(id);
            updateCourseRatings(rating.getCourseId());
            return true;
        }
        return false;
    }
    public List<CourseRating> getCourseRatingByEmailId(String emailId) {
        return courseRatingRepository.findByEmailId(emailId);
    }


    private double calculateOverallRating(CourseRating rating) {
        double overallRating = 0;
        overallRating += rating.getDificulity();
        overallRating += rating.getAdministration();
        overallRating += rating.getCuriculum();
        overallRating += rating.getAlumniNetwork();
        overallRating += rating.getFacilities();
        overallRating += rating.getPeers();
        overallRating += rating.getPlacements();
        overallRating += rating.getPracticality();
        overallRating += rating.getTeaching();
        overallRating /= 9;
        return overallRating;
    }
    private void updateCourseRatings(String courseId) {
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
    
            // Update total reviews count
            long reviewCount = courseRatingRepository.countByCourseId(courseId);
            course.setTotalReviews(reviewCount);
            System.out.println("Overall rating " + courseRatingRepository.getAverageOverallRating(courseId) );
            // Update average ratings for each category
            course.setOverallRating(getAverageValue(courseRatingRepository.getAverageOverallRating(courseId)));
            course.setTeaching(getAverageValue(courseRatingRepository.getAverageTeaching(courseId)));
            course.setPracticality(getAverageValue(courseRatingRepository.getAveragePracticality(courseId)));
            course.setPlacements(getAverageValue(courseRatingRepository.getAveragePlacements(courseId)));
            course.setCuriculum(getAverageValue(courseRatingRepository.getAverageCuriculum(courseId)));
            course.setFacilities(getAverageValue(courseRatingRepository.getAverageFacilities(courseId)));
            course.setDificulity(getAverageValue(courseRatingRepository.getAverageDificulity(courseId)));
            course.setAdministration(getAverageValue(courseRatingRepository.getAverageAdministration(courseId)));
            course.setAlumniNetwork(getAverageValue(courseRatingRepository.getAverageAlumniNetwork(courseId)));
            course.setPeers(getAverageValue(courseRatingRepository.getAveragePeers(courseId)));
    
            // Save the updated course object
            courseRepository.save(course);
        }
    }
    

private double getAverageValue(Double average) {
    return average != null ? average : 0.0;
}

}
