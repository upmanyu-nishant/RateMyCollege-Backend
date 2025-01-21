package com.project.rate_my_college.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "ratingCards")
public class RatingCard {
    @Id
    private String id;
    private String collegeId;  // Reference to College ID
	private String emailId;
    private double overallRating;
    private double reputation;
    private double locationRating;
    private double opportunities;
    private double facilities;
    private double internet;
    private double food;
    private double clubs;
    private double social;
    private double happiness;
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getCollegeId() {
		return collegeId;
	}
	public void setCollegeId(String collegeId) {
		this.collegeId = collegeId;
	}
	public double getOverallRating() {
		return overallRating;
	}
	public void setOverallRating(double overallRating) {
		this.overallRating = overallRating;
	}
	public double getReputation() {
		return reputation;
	}
	public void setReputation(double reputation) {
		this.reputation = reputation;
	}
	public double getLocationRating() {
		return locationRating;
	}
	public void setLocationRating(double locationRating) {
		this.locationRating = locationRating;
	}
	public double getOpportunities() {
		return opportunities;
	}
	public void setOpportunities(double opportunities) {
		this.opportunities = opportunities;
	}
	public double getFacilities() {
		return facilities;
	}
	public void setFacilities(double facilities) {
		this.facilities = facilities;
	}
	public double getInternet() {
		return internet;
	}
	public void setInternet(double internet) {
		this.internet = internet;
	}
	public double getFood() {
		return food;
	}
	public void setFood(double food) {
		this.food = food;
	}
	public double getClubs() {
		return clubs;
	}
	public void setClubs(double clubs) {
		this.clubs = clubs;
	}
	public double getSocial() {
		return social;
	}
	public void setSocial(double social) {
		this.social = social;
	}
	public double getHappiness() {
		return happiness;
	}
	public void setHappiness(double happiness) {
		this.happiness = happiness;
	}
	public double getSafety() {
		return safety;
	}
	public void setSafety(double safety) {
		this.safety = safety;
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
	private double safety;
    private boolean approved;  // Whether the review is approved
    private Date date;
    private int helpfulCount;
    private int notHelpfulCount;
    private int reportCount;
    private String comment;

    // Getters and Setters
}
