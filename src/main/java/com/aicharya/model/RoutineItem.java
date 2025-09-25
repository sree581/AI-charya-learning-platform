package com.aicharya.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.util.Date;

@Entity
public class RoutineItem {
    
    @Id
    private String itemId; // from -itemId: String
    
    private Date scheduledDate;
    private String scheduledTimeSlot;
    private boolean completionStatus;

    @ManyToOne // An item points to one learning resource
    private LearningResource learningResource;
    
    // Getters and Setters
    public String getItemId() { return itemId; }
    public void setItemId(String itemId) { this.itemId = itemId; }
    // ... etc.
}