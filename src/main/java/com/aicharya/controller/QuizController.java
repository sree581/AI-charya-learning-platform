package com.aicharya.controller;

import com.aicharya.SceneManager;
import com.aicharya.service.AIPersonalizationEngine; // ✅ Import the AI service
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuizController implements SceneManager.DataReceiver {

    @Autowired
    private SceneManager sceneManager;

    // ✅ Inject your AI service
    @Autowired
    private AIPersonalizationEngine aiPersonalizationEngine;

    @FXML
    private TextArea quizTextArea;

    @Override
    public void setData(Object data) {
        if (data instanceof String subtopicName) {
            quizTextArea.setText("Generating quiz for: " + subtopicName + "..."); // Show loading message
            // ✅ Call the ACTUAL AI service to generate the quiz
            String quizContent = aiPersonalizationEngine.generateQuiz(subtopicName);
            quizTextArea.setText(quizContent); // Display the AI-generated quiz
        } else {
            quizTextArea.setText("Error: Could not load quiz topic.");
        }
    }

    /*
     * ❌ REMOVE the old placeholder method:
     * private String generateQuiz(String subtopic) { ... }
     */

    @FXML
    private void submitQuiz() {
        System.out.println("Quiz submitted!");
        // TODO: Handle quiz submission, grading, and analysis
    }

    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        System.out.println(">>> Back button clicked in Quiz. Navigating back.");
        // Ensure this path is correct
        sceneManager.showScene("/com/aicharya/view/subtopic-detail-view.fxml", "Subtopic Details");
    }
}