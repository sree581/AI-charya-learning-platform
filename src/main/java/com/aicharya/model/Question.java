package com.aicharya.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.List;

@Entity
public class Question {
    @Id
    private String questionId;
    private String text;
    private List<String> options;
    private int correctOptionIndex;

    // Constructors, Getters, and Setters
    public Question() {}

    public Question(String questionId, String text, List<String> options, int correctOptionIndex) {
        this.questionId = questionId;
        this.text = text;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }
    
    // You can generate the rest of the getters and setters in VS Code
    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }
}