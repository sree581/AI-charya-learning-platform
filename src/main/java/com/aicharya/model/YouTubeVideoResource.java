package com.aicharya.model;

import jakarta.persistence.Entity;

@Entity
public class YouTubeVideoResource extends LearningResource { // Extends the base class

    private String youtubeUrl; // from -youtubeUrl: String
    private int duration; // from -duration: int
    private double averageRating; // from -averageRating: double
    private int totalRatings; // from -totalRatings: int

    // Getters and Setters
    public String getYoutubeUrl() { return youtubeUrl; }
    public void setYoutubeUrl(String youtubeUrl) { this.youtubeUrl = youtubeUrl; }
    // ... etc.
}