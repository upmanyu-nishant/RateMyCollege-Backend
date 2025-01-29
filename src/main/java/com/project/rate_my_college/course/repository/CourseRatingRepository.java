package com.project.rate_my_college.course.repository;

import java.util.List;


import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.rate_my_college.course.model.CourseRating;

public interface CourseRatingRepository extends MongoRepository<CourseRating, String> {
    
    // Fetch course ratings by Course ID
    List<CourseRating> findByCourseId(String courseId);
    List<CourseRating> findByEmailId(String emailId);

    long countByCourseId(String courseId);
    
// Method to calculate the average values for each field

@Aggregation(pipeline = {
    "{ '$match': { 'courseId': ?0 } }",
    "{ '$group': { '_id': null, 'averageOverallRating': { '$avg': '$overallRating' } } }"
})
Double getAverageOverallRating(String courseId);

@Aggregation(pipeline = {
    "{ '$match': { 'courseId': ?0 } }",
    "{ '$group': { '_id': null, 'averageTeaching': { '$avg': '$teaching' } } }"
})
Double getAverageTeaching(String courseId);

@Aggregation(pipeline = {
    "{ '$match': { 'courseId': ?0 } }",
    "{ '$group': { '_id': null, 'averagePracticality': { '$avg': '$practicality' } } }"
})
Double getAveragePracticality(String courseId);

@Aggregation(pipeline = {
    "{ '$match': { 'courseId': ?0 } }",
    "{ '$group': { '_id': null, 'averagePlacements': { '$avg': '$placements' } } }"
})
Double getAveragePlacements(String courseId);

@Aggregation(pipeline = {
    "{ '$match': { 'courseId': ?0 } }",
    "{ '$group': { '_id': null, 'averageCuriculum': { '$avg': '$curiculum' } } }"
})
Double getAverageCuriculum(String courseId);

@Aggregation(pipeline = {
    "{ '$match': { 'courseId': ?0 } }",
    "{ '$group': { '_id': null, 'averageFacilities': { '$avg': '$facilities' } } }"
})
Double getAverageFacilities(String courseId);

@Aggregation(pipeline = {
    "{ '$match': { 'courseId': ?0 } }",
    "{ '$group': { '_id': null, 'averageDificulity': { '$avg': '$dificulity' } } }"
})
Double getAverageDificulity(String courseId);

@Aggregation(pipeline = {
    "{ '$match': { 'courseId': ?0 } }",
    "{ '$group': { '_id': null, 'averageAdministration': { '$avg': '$administration' } } }"
})
Double getAverageAdministration(String courseId);

@Aggregation(pipeline = {
    "{ '$match': { 'courseId': ?0 } }",
    "{ '$group': { '_id': null, 'averageAlumniNetwork': { '$avg': '$alumniNetwork' } } }"
})
Double getAverageAlumniNetwork(String courseId);

@Aggregation(pipeline = {
    "{ '$match': { 'courseId': ?0 } }",
    "{ '$group': { '_id': null, 'averagePeers': { '$avg': '$peers' } } }"
})
Double getAveragePeers(String courseId);


}
