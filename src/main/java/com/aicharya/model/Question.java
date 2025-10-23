package com.aicharya.model;

import java.util.List;

public class Question {
    private String questionText;
    private List<String> options;
    private int correctOptionIndex;

    /**
     * A no-argument constructor is required for libraries like Gson/Jackson to instantiate the object.
     */
    public Question() {
    }

    /**
     * A constructor with all fields for convenience.
     * @param questionText The text of the question.
     * @param options A list of possible answers.
     * @param correctOptionIndex The 0-based index of the correct answer in the options list.
     */
    public Question(String questionText, List<String> options, int correctOptionIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    // --- Getters and Setters ---

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }

    public void setCorrectOptionIndex(int correctOptionIndex) {
        this.correctOptionIndex = correctOptionIndex;
    }
}