package com.aicharya.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
public class PersonalizedRoutine {

    @Id
    private String routineId; // from -routineId: String

    private Date creationDate;
    private String status;

    @OneToMany(cascade = CascadeType.ALL) // A routine has many items
    private List<RoutineItem> routineItems;
    
    // Getters and Setters
    public String getRoutineId() { return routineId; }
    public void setRoutineId(String routineId) { this.routineId = routineId; }
    // ... etc.
}