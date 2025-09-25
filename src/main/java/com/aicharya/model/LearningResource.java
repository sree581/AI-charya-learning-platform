package com.aicharya.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Tells JPA how to handle inheritance
public abstract class LearningResource { // Abstract: cannot be created directly

    @Id
    private String resourceId; // from -resourceId: String

    private String title;
    private String description;
    private String topic;
    private int difficultyLevel;

    @ManyToOne
    private Subject subject;

    // Getters and Setters
    public String getResourceId() { return resourceId; }
    public void setResourceId(String resourceId) { this.resourceId = resourceId; }
    // ... etc.
}