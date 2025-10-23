package com.aicharya.controller;

import com.aicharya.SceneManager;
import com.aicharya.service.UserService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginController {

    @Autowired
    private SceneManager sceneManager;

    @Autowired
    private UserService userService;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;
    
    @FXML
    private Button closeBtn;
    @FXML
    private Button minBtn;
    @FXML
    private Button maxBtn;

    private boolean maximized = false;

    @FXML
    public void initialize() {
        // Close Button
        if (closeBtn != null) {
            closeBtn.setOnAction(e -> Platform.exit());
        }

        // Minimize Button
        if (minBtn != null) {
            minBtn.setOnAction(e -> {
                Stage stage = (Stage) minBtn.getScene().getWindow();
                stage.setIconified(true);
            });
        }

        // Maximize / Restore Button
        if (maxBtn != null) {
            maxBtn.setOnAction(e -> {
                Stage stage = (Stage) maxBtn.getScene().getWindow();
                if (maximized) {
                    stage.setMaximized(false);
                    maximized = false;
                } else {
                    stage.setMaximized(true);
                    maximized = true;
                }
            });
        }
    }

    @FXML
    void handleLoginButtonAction(ActionEvent event) {
        String email = usernameField.getText();
        String password = passwordField.getText();

        if (userService.isValidLogin(email, password)) {
            System.out.println(">>> Login successful! Navigating to dashboard.");
            // This path was already correct
            sceneManager.showScene("/com/aicharya/view/dashboard-view.fxml", "Dashboard");
        } else {
            showAlert("Login Failed", "Invalid email or password. Please try again.");
        }
    }
    
    @FXML
    void handleRegisterLinkAction(ActionEvent event) {
        // ✅ EDITED: Corrected the file path
        sceneManager.showScene("/com/aicharya/view/register-view.fxml", "Aicharya Register");
    }

    @FXML
    void handleForgotPasswordLinkAction(ActionEvent event) {
        // ✅ EDITED: Corrected the file path
        sceneManager.showScene("/com/aicharya/view/forgot-password-view.fxml", "Forgot Password");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}