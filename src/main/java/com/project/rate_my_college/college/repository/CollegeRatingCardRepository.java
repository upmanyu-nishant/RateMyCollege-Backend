package com.project.rate_my_college.college.repository;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.rate_my_college.college.model.CollegeRatingCard;

import java.util.List;

public interface CollegeRatingCardRepository extends MongoRepository<CollegeRatingCard, String> {
    List<CollegeRatingCard> findByCollegeId(String collegeId);
    List<CollegeRatingCard> findByEmailId(String emailId);  // Find rating cards by emailId
    long countByCollegeId(String collegeId);
    
 // Method to calculate average values for each field
    @Aggregation(pipeline = {
            "{ '$match': { 'collegeId': ?0 } }",
            "{ '$group': { '_id': null, 'averageRating': { '$avg': '$overallRating' } } }"
    })
    Double getAverageOverallRating(String collegeId);

    @Aggregation(pipeline = {
            "{ '$match': { 'collegeId': ?0 } }",
            "{ '$group': { '_id': null, 'averageReputation': { '$avg': '$reputation' } } }"
    })
    Double getAverageReputation(String collegeId);

    @Aggregation(pipeline = {
            "{ '$match': { 'collegeId': ?0 } }",
            "{ '$group': { '_id': null, 'averageLocationRating': { '$avg': '$locationRating' } } }"
    })
    Double getAverageLocationRating(String collegeId);

    @Aggregation(pipeline = {
            "{ '$match': { 'collegeId': ?0 } }",
            "{ '$group': { '_id': null, 'averageOpportunities': { '$avg': '$opportunities' } } }"
    })
    Double getAverageOpportunities(String collegeId);

    @Aggregation(pipeline = {
            "{ '$match': { 'collegeId': ?0 } }",
            "{ '$group': { '_id': null, 'averageFacilities': { '$avg': '$facilities' } } }"
    })
    Double getAverageFacilities(String collegeId);

    @Aggregation(pipeline = {
            "{ '$match': { 'collegeId': ?0 } }",
            "{ '$group': { '_id': null, 'averageInternet': { '$avg': '$internet' } } }"
    })
    Double getAverageInternet(String collegeId);

    @Aggregation(pipeline = {
            "{ '$match': { 'collegeId': ?0 } }",
            "{ '$group': { '_id': null, 'averageFood': { '$avg': '$food' } } }"
    })
    Double getAverageFood(String collegeId);

    @Aggregation(pipeline = {
            "{ '$match': { 'collegeId': ?0 } }",
            "{ '$group': { '_id': null, 'averageClubs': { '$avg': '$clubs' } } }"
    })
    Double getAverageClubs(String collegeId);

    @Aggregation(pipeline = {
            "{ '$match': { 'collegeId': ?0 } }",
            "{ '$group': { '_id': null, 'averageSocial': { '$avg': '$social' } } }"
    })
    Double getAverageSocial(String collegeId);

    @Aggregation(pipeline = {
            "{ '$match': { 'collegeId': ?0 } }",
            "{ '$group': { '_id': null, 'averageHappiness': { '$avg': '$happiness' } } }"
    })
    Double getAverageHappiness(String collegeId);

    @Aggregation(pipeline = {
            "{ '$match': { 'collegeId': ?0 } }",
            "{ '$group': { '_id': null, 'averageSafety': { '$avg': '$safety' } } }"
    })
    Double getAverageSafety(String collegeId);

}
