package com.aicharya.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import java.util.List;

@Entity
public class Student extends User {

    @ElementCollection // For a simple list of Strings
    private List<String> interests; // from -interests: List

    private String academicHabits; // from -academicHabits: String

    @OneToOne(cascade = CascadeType.ALL) // Establishes the link to the ProgressTracker
    private ProgressTracker progressTracker;

    // Getters and Setters
    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    public String getAcademicHabits() {
        return academicHabits;
    }

    public void setAcademicHabits(String academicHabits) {
        this.academicHabits = academicHabits;
    }

    public ProgressTracker getProgressTracker() {
        return progressTracker;
    }

    public void setProgressTracker(ProgressTracker progressTracker) {
        this.progressTracker = progressTracker;
    }
}