package com.aicharya.controller;

import com.aicharya.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginController {

    // --- MOCK USER DATABASE ---
    // In a real application, this would come from a database.
    private final String CORRECT_USERNAME = "user";
    private final String CORRECT_PASSWORD = "password123";
    // -------------------------

    @Autowired
    private SceneManager sceneManager;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    void handleLoginButtonAction(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.equals(CORRECT_USERNAME) && password.equals(CORRECT_PASSWORD)) {
            // If credentials are correct, navigate to the dashboard
            System.out.println(">>> Login successful! Navigating to dashboard.");
            sceneManager.showScene("dashboard-view.fxml", "Dashboard");
        } else {
            // If credentials are NOT correct, show an error message
            showAlert("Login Failed", "Invalid username or password. Please try again.");
        }
    }
    
    @FXML
    void handleRegisterLinkAction(ActionEvent event) {
        // Navigate to the register screen
        sceneManager.showScene("register-view.fxml", "Aicharya Register");
    }
    @FXML
    void handleForgotPasswordLinkAction(ActionEvent event) {
         sceneManager.showScene("forgot-password-view.fxml", "Forgot Password");
    }
    // Helper method to show an alert
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}