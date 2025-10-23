package com.aicharya.controller;

import com.aicharya.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ForgotPasswordController {

    @Autowired
    private SceneManager sceneManager;

    @FXML
    void handleChangePasswordAction(ActionEvent event) {
        System.out.println(">>> 'Change Password' button clicked. Navigating to login.");
        // ✅ EDITED: Corrected the file path
        sceneManager.showScene("/com/aicharya/view/login-view.fxml", "Aicharya Login");
    }

    @FXML
    void handleBackToLoginAction(ActionEvent event) {
        // ✅ EDITED: Corrected the file path
        sceneManager.showScene("/com/aicharya/view/login-view.fxml", "Aicharya Login");
    }
}