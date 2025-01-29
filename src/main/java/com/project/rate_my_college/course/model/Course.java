package com.project.rate_my_college.course.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "courses")
public class Course {
    @Id
    private String id;
    private String name;
    private String collegeId;  // Reference to College ID
    private String department;
    private String level;
    private Double overallRating;
    private long totalReviews;
    private Double teaching;
    private Double practicality;
    private Double placements;
    private Double curiculum;
    private Double facilities;
    private Double dificulity;
    private Double administration;
    private Double alumniNetwork;
    private Double peers;
  

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Double getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(Double overallRating) {
        this.overallRating = overallRating;
    }

    public long getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(long totalReviews) {
        this.totalReviews = totalReviews;
    }

    public Double getTeaching() {
        return teaching;
    }

    public void setTeaching(Double teaching) {
        this.teaching = teaching;
    }

    public Double getPracticality() {
        return practicality;
    }

    public void setPracticality(Double practicality) {
        this.practicality = practicality;
    }

    public Double getPlacements() {
        return placements;
    }

    public void setPlacements(Double placements) {
        this.placements = placements;
    }

    public Double getCuriculum() {
        return curiculum;
    }

    public void setCuriculum(Double curiculum) {
        this.curiculum = curiculum;
    }

    public Double getFacilities() {
        return facilities;
    }

    public void setFacilities(Double facilities) {
        this.facilities = facilities;
    }

    public Double getDificulity() {
        return dificulity;
    }

    public void setDificulity(Double dificulity) {
        this.dificulity = dificulity;
    }

    public Double getAdministration() {
        return administration;
    }

    public void setAdministration(Double administration) {
        this.administration = administration;
    }

    public Double getAlumniNetwork() {
        return alumniNetwork;
    }

    public void setAlumniNetwork(Double alumniNetwork) {
        this.alumniNetwork = alumniNetwork;
    }

    public Double getPeers() {
        return peers;
    }

    public void setPeers(Double peers) {
        this.peers = peers;
    }
   
}
