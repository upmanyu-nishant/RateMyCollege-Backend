package com.project.rate_my_college.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "colleges")
public class College {
    @Id
    private String id;
    private String name;
    private String location;
    private double overallRating;  // Average overall rating
    private int totalReviews;  // Total number of reviews
    private double reputation;
    private double locationRating;
    private double opportunities;
    private double facilities;
    private double internet;
    private double food;
    private double clubs;
    private double social;
    private double happiness;
    private double safety;

    // Getters and Setters
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(double overallRating) {
        this.overallRating = overallRating;
    }

    public int getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(int totalReviews) {
        this.totalReviews = totalReviews;
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
}
