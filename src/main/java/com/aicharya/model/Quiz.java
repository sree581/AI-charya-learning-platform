package com.aicharya.model;

import java.util.List;

public class Quiz {
    private String title;
    private List<Question> questions;

    /**
     * A no-argument constructor is required for libraries like Gson/Jackson to instantiate the object.
     */
    public Quiz() {
    }

    /**
     * A constructor with all fields for convenience.
     * @param title The title of the quiz.
     * @param questions The list of questions in the quiz.
     */
    public Quiz(String title, List<Question> questions) {
        this.title = title;
        this.questions = questions;
    }

    // --- Getters and Setters ---

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}