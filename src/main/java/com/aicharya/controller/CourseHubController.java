package com.aicharya.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aicharya.SceneManager;

import java.io.IOException;
import java.net.URL;

@Component
public class CourseHubController {

    @Autowired
    private SceneManager sceneManager; // <-- inject SceneManager

    /**
     * Handles the "QUIZ" button click.
     * Removed GeminiService references; you can now manually load quiz scene.
     */
    @FXML
    private void handleQuizButtonAction(ActionEvent event) {
        try {
            URL fxmlLocation = getClass().getResource("/com/aicharya/view/quiz-view.fxml");
            if (fxmlLocation == null) {
                System.err.println("Cannot find FXML file: quiz-view.fxml");
                return;
            }
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();
            // QuizController quizController = loader.getController();
            // quizController.initData(...); // manually pass data if needed

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Quiz");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleVideosButtonAction(ActionEvent event) {
        System.out.println("Videos button clicked - navigating to videos view...");
        // TODO: load videos scene
    }

    @FXML
    private void handleProgressButtonAction(ActionEvent event) {
        System.out.println("Progress Chart button clicked - navigating to progress view...");
        // TODO: load progress chart scene
    }

    @FXML
    private void handleWeakAreasButtonAction(ActionEvent event) {
        System.out.println("Weak Areas button clicked - navigating to weak areas view...");
        // TODO: load weak areas scene
    }

    @FXML
private void handleBackToSubtopicsAction(ActionEvent event) {
    System.out.println("Back button clicked - navigating to subtopics view...");
    
    // Replace with your subtopics FXML path and title
    sceneManager.showScene("/com/aicharya/view/subtopics-view.fxml", "Subtopics");
}

}
