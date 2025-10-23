package com.aicharya.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) 
public abstract class LearningResource { 

    @Id
    private String resourceId; 

    private String title;
    private String description;
    private String topic;
    private int difficultyLevel;

    @ManyToOne
    private Subject subject;

    
    public String getResourceId() { return resourceId; }
    public void setResourceId(String resourceId) { this.resourceId = resourceId; }
   
}