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
        // ✅ EDITED: Corrected the file path
        sceneManager.showScene("/com/aicharya/view/dashboard-view.fxml", "Dashboard");
    }

    @FXML
    void handleBackButtonAction(ActionEvent event) {
        // ✅ EDITED: Corrected the file path
        sceneManager.showScene("/com/aicharya/view/dashboard-view.fxml", "Dashboard");
    }
}