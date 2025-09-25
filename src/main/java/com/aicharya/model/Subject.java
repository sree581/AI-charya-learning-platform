package com.aicharya.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Subject {

    @Id
    private String subjectId; // from -subjectid: String

    private String name;
    private String description;

    // Getters and Setters
    public String getSubjectId() { return subjectId; }
    public void setSubjectId(String subjectId) { this.subjectId = subjectId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}