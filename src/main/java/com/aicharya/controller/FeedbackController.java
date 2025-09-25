package com.aicharya.controller;

import com.aicharya.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeedbackController {

    @Autowired
    private SceneManager sceneManager;

    @FXML
    void handleSubmitFeedbackAction(ActionEvent event) {
        System.out.println(">>> Feedback submitted. Thank you!");
        sceneManager.showScene("dashboard-view.fxml", "Dashboard");
    }

    @FXML
    void handleBackButtonAction(ActionEvent event) {
        sceneManager.showScene("dashboard-view.fxml", "Dashboard");
    }
}