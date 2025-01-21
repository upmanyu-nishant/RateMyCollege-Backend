package com.project.rate_my_college.service;

import com.project.rate_my_college.model.College;
import com.project.rate_my_college.repository.CollegeRepository;
import com.project.rate_my_college.repository.RatingCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CollegeService {

    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private RatingCardRepository ratingCardRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    public List<College> getAllColleges() {
        List<College> colleges = collegeRepository.findAll();
        for (College college : colleges) {
            updateCollegeRatings(college);
        }
        return colleges;
    }

    public College addCollege(College college) {
        long id = sequenceGeneratorService.getNextSequenceId("colleges");  // Generate auto-increment ID
        college.setId(String.valueOf(id));  // Set the numeric ID
        return collegeRepository.save(college);
    }
    
    public List<College> addColleges(List<College> colleges) {
        return colleges.stream().map(college -> {
            long id = sequenceGeneratorService.getNextSequenceId("colleges");  // Generate auto-increment ID
            college.setId(String.valueOf(id));
            return collegeRepository.save(college);
        }).collect(Collectors.toList());
    }

    public College getCollegeById(String id) {
        Optional<College> optionalCollege = collegeRepository.findById(id);
        if (optionalCollege.isPresent()) {
            College college = optionalCollege.get();
            updateCollegeRatings(college);
            return college;
        }
        return null;
    }

    public boolean deleteCollege(String id) {
        if (collegeRepository.existsById(id)) {
            collegeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<College> searchColleges(String name, String location) {
        if (name != null && location != null) {
            return collegeRepository.findByNameAndLocationContainingIgnoreCase(name, location);
        } else if (name != null) {
            return collegeRepository.findByNameContainingIgnoreCase(name);
        } else if (location != null) {
            return collegeRepository.findByLocationContainingIgnoreCase(location);
        } else {
            return collegeRepository.findAll();  // Return all if no search criteria
        }
    }

    public College updateCollege(String id, Map<String, Object> updates) {
        Optional<College> optionalCollege = collegeRepository.findById(id);
        if (optionalCollege.isPresent()) {
            College college = optionalCollege.get();
            
            // Allowing direct update of some fields
            updates.forEach((key, value) -> {
                switch (key) {
                    case "overallRating":
                        college.setOverallRating(((Number) value).doubleValue());
                        break;
                    case "reputation":
                        college.setReputation(((Number) value).doubleValue());
                        break;
                    case "locationRating":
                        college.setLocationRating(((Number) value).doubleValue());
                        break;
                    case "opportunities":
                        college.setOpportunities(((Number) value).doubleValue());
                        break;
                    case "facilities":
                        college.setFacilities(((Number) value).doubleValue());
                        break;
                    case "internet":
                        college.setInternet(((Number) value).doubleValue());
                        break;
                    case "food":
                        college.setFood(((Number) value).doubleValue());
                        break;
                    case "clubs":
                        college.setClubs(((Number) value).doubleValue());
                        break;
                    case "social":
                        college.setSocial(((Number) value).doubleValue());
                        break;
                    case "happiness":
                        college.setHappiness(((Number) value).doubleValue());
                        break;
                    case "safety":
                        college.setSafety(((Number) value).doubleValue());
                        break;
                }
            });

            // Update average ratings from RatingCard
            updateCollegeRatings(college);

            return collegeRepository.save(college);
        }
        return null;
    }

    private void updateCollegeRatings(College college) {
        String collegeId = college.getId();

        // Set total reviews count
        long reviewCount = ratingCardRepository.countByCollegeId(collegeId);
        college.setTotalReviews((int) reviewCount);

        // Set average values for all rating fields
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
        collegeRepository.save(college);
    }

    private double getAverageValue(Double average) {
        return average != null ? average : 0.0;
    }
}
