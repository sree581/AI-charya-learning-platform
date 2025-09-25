package com.aicharya.controller;

import com.aicharya.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseHubController {

    @Autowired
    private SceneManager sceneManager;

    @FXML
    void handleQuizButtonAction(ActionEvent event) {
        sceneManager.showScene("quiz-view.fxml", "Quiz in Progress");
    }

    @FXML
    void handleVideosButtonAction(ActionEvent event) {
        sceneManager.showScene("videos-view.fxml", "Videos");
    }

    @FXML
    void handleProgressButtonAction(ActionEvent event) {
        sceneManager.showScene("progress-chart-view.fxml", "Progress Chart");
    }
    
    @FXML
    void handleWeakAreasButtonAction(ActionEvent event) {
        sceneManager.showScene("weak-areas-view.fxml", "Weak Areas");
    }

    @FXML
    void handleBackToSubtopicsAction(ActionEvent event) {
        sceneManager.showScene("subtopics-view.fxml", "Course Subtopics");
    }
}