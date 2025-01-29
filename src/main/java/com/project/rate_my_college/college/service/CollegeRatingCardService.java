package com.project.rate_my_college.college.service;

import com.project.rate_my_college.college.model.College;
import com.project.rate_my_college.college.model.CollegeRatingCard;
import com.project.rate_my_college.college.repository.CollegeRepository;
import com.project.rate_my_college.college.repository.CollegeRatingCardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CollegeRatingCardService {

    @Autowired
    private CollegeRatingCardRepository ratingCardRepository;

    @Autowired
    private CollegeRepository collegeRepository;

    public List<CollegeRatingCard> getRatingsByCollegeId(String collegeId) {
        return ratingCardRepository.findByCollegeId(collegeId);
    }

    public List<CollegeRatingCard> getRatingsByEmailId(String emailId) {
        return ratingCardRepository.findByEmailId(emailId);  // Fetch rating cards by emailId
    }

    public CollegeRatingCard addRatingCard(CollegeRatingCard ratingCard) {
        double overallRating = calculateOverallRating(ratingCard);
        ratingCard.setOverallRating(overallRating);  // Set the overall rating as the average of all other ratings

        CollegeRatingCard savedRatingCard = ratingCardRepository.save(ratingCard);

        // Update the associated college's ratings
        updateCollegeRatings(ratingCard.getCollegeId());

        return savedRatingCard;
    }

    public boolean deleteRatingCard(String id) {
        Optional<CollegeRatingCard> optionalRatingCard = ratingCardRepository.findById(id);
        if (optionalRatingCard.isPresent()) {
            CollegeRatingCard ratingCard = optionalRatingCard.get();
            ratingCardRepository.deleteById(id);

            // Update the associated college's ratings
            updateCollegeRatings(ratingCard.getCollegeId());
            return true;
        }
        return false;
    }

    public CollegeRatingCard updateRatingCard(String id, Map<String, Object> updates) {
        Optional<CollegeRatingCard> optionalRatingCard = ratingCardRepository.findById(id);
        if (optionalRatingCard.isPresent()) {
            CollegeRatingCard ratingCard = optionalRatingCard.get();

            // Update fields if they exist in the request body
            if (updates.containsKey("approved")) {
                ratingCard.setApproved((Boolean) updates.get("approved"));
            }
            if (updates.containsKey("helpfulCount")) {
                ratingCard.setHelpfulCount((Integer) updates.get("helpfulCount"));
            }
            if (updates.containsKey("notHelpfulCount")) {
                ratingCard.setNotHelpfulCount((Integer) updates.get("notHelpfulCount"));
            }
            if (updates.containsKey("reportCount")) {
                ratingCard.setReportCount((Integer) updates.get("reportCount"));
            }

            CollegeRatingCard updatedRatingCard = ratingCardRepository.save(ratingCard);

            // Update the associated college's ratings
            updateCollegeRatings(ratingCard.getCollegeId());

            return updatedRatingCard;
        }
        return null;  // Return null if not found
    }

    private double calculateOverallRating(CollegeRatingCard ratingCard) {
        double sum = ratingCard.getReputation()
                + ratingCard.getLocationRating()
                + ratingCard.getOpportunities()
                + ratingCard.getFacilities()
                + ratingCard.getInternet()
                + ratingCard.getFood()
                + ratingCard.getClubs()
                + ratingCard.getSocial()
                + ratingCard.getHappiness()
                + ratingCard.getSafety();
        return sum / 10;  // Average of 10 categories
    }

    private void updateCollegeRatings(String collegeId) {
        Optional<College> optionalCollege = collegeRepository.findById(collegeId);
        if (optionalCollege.isPresent()) {
            College college = optionalCollege.get();

            // Update total reviews count
            long reviewCount = ratingCardRepository.countByCollegeId(collegeId);
            college.setTotalReviews((int) reviewCount);
            // Update average ratings for each category
            college.setOverallRating(getAverageValue(ratingCardRepository.getAverageOverallRating(collegeId)));
            college.setReputation(getAverageValue(ratingCardRepository.getAverageReputation(collegeId)));
            college.setLocationRating(getAverageValue(ratingCardRepository.getAverageLocationRating(collegeId)));
            college.setOpportunities(getAverageValue(ratingCardRepository.getAverageOpportunities(collegeId)));
            college.setFacilities(getAverageValue(ratingCardRepository.getAverageFacilities(collegeId)));
            college.setInternet(getAverageValue(ratingCardRepository.getAverageInternet(collegeId)));
            college.setFood(getAverageValue(ratingCardRepository.getAverageFood(collegeId)));
            college.setClubs(getAverageValue(ratingCardRepository.getAverageClubs(collegeId)));
            college.setSocial(getAverageValue(ratingCardRepository.getAverageSocial(collegeId)));
            college.setHappiness(getAverageValue(ratingCardRepository.getAverageHappiness(collegeId)));
            college.setSafety(getAverageValue(ratingCardRepository.getAverageSafety(collegeId)));

            // Save the updated college object
            collegeRepository.save(college);
        }
    }

    private double getAverageValue(Double average) {
        return average != null ? average : 0.0;
    }
}
