package com.project.rate_my_college.repository;
import org.springframework.data.mongodb.repository.Aggregation;


import com.project.rate_my_college.model.RatingCard;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface RatingCardRepository extends MongoRepository<RatingCard, String> {
    List<RatingCard> findByCollegeId(String collegeId);
    List<RatingCard> findByEmailId(String emailId);  // Find rating cards by emailId
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
