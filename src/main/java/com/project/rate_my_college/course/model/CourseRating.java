package com.project.rate_my_college.course.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "courseRatings")
public class CourseRating {
    
    @Id
    private String id;
    private String courseId;  
    private String collegeId;
    private String emailId;
    private String department;
    private String level;
    private Double overallRating;
    private Double teaching;
    private Double practicality;
    private Double placements;
    private Double curiculum;
    private Double facilities;
    private Double dificulity;
    private Double administration;
    private Double alumniNetwork;
    private Double peers;
    private boolean approved;  
    private Date date;
    private int helpfulCount;
    private int notHelpfulCount;
    private int reportCount;
    private String comment;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
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
    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getHelpfulCount() {
        return helpfulCount;
    }

    public void setHelpfulCount(int helpfulCount) {
        this.helpfulCount = helpfulCount;
    }

    public int getNotHelpfulCount() {
        return notHelpfulCount;
    }

    public void setNotHelpfulCount(int notHelpfulCount) {
        this.notHelpfulCount = notHelpfulCount;
    }

    public int getReportCount() {
        return reportCount;
    }

    public void setReportCount(int reportCount) {
        this.reportCount = reportCount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
