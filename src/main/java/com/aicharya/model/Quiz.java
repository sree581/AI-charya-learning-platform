package com.aicharya.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Quiz {
    @Id
    private String quizId;
    private String title;
    private String topic;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Question> questions;

    // Constructors, Getters, and Setters
    public Quiz() {}

    public Quiz(String quizId, String title, String topic, List<Question> questions) {
        this.quizId = quizId;
        this.title = title;
        this.topic = topic;
        this.questions = questions;
    }

    // You can generate the rest of the getters and setters in VS Code
    public String getQuizId() {
        return quizId;
    }

    public String getTitle() {
        return title;
    }
    
    public List<Question> getQuestions() {
        return questions;
    }
}