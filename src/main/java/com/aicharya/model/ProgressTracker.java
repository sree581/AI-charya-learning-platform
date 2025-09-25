package com.aicharya.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.List;

@Entity
public class ProgressTracker {

    @Id
    private String trackerId; // from -trackerId: String

    @ElementCollection
    private List<String> quizHistory; // from -quizHistory: List (Simplified to a list of Quiz IDs)

    @ElementCollection
    private List<String> completedRoutines; // from -completedRoutines: List (Simplified to a list of Routine IDs)

    // Getters and Setters
    public String getTrackerId() { return trackerId; }
    public void setTrackerId(String trackerId) { this.trackerId = trackerId; }
    public List<String> getQuizHistory() { return quizHistory; }
    public void setQuizHistory(List<String> quizHistory) { this.quizHistory = quizHistory; }
    public List<String> getCompletedRoutines() { return completedRoutines; }
    public void setCompletedRoutines(List<String> completedRoutines) { this.completedRoutines = completedRoutines; }
}