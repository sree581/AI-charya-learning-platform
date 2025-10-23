package com.aicharya.controller;

import com.aicharya.SceneManager;
import com.aicharya.service.UserService; // Import the new service
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Component
public class RegisterController {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Autowired
    private SceneManager sceneManager;
    
    @Autowired
    private UserService userService; // Ask Spring for our new user service

    @FXML private TextField nameField;
    @FXML private ComboBox<String> eduLevelComboBox;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private CheckBox termsCheckBox;

    @FXML
    public void initialize() {
        List<String> levels = List.of(
            "Class 1", "Class 2", "Class 3", "Class 4", "Class 5", "Class 6",
            "Class 7", "Class 8", "Class 9", "Class 10", "Class 11", "Class 12",
            "Undergraduate", "Postgraduate"
        );
        eduLevelComboBox.setItems(FXCollections.observableArrayList(levels));
    }

    @FXML
    void handleRegisterButtonAction(ActionEvent event) {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (!validateInput(name, email, password, confirmPassword)) {
            return; // Stop if validation fails
        }

        // If validation passes, register the user via the service
        userService.registerUser(name, email, password);

        System.out.println(">>> User registered successfully! Navigating to login.");
        sceneManager.showScene("/com/aicharya/view/login-view.fxml", "Aicharya Login");
    }

    @FXML
    void handleLoginLinkAction(ActionEvent event) {
       sceneManager.showScene("/com/aicharya/view/login-view.fxml", "Aicharya Login");
    }

    private boolean validateInput(String name, String email, String password, String confirmPassword) {
        if (name.isBlank() || email.isBlank() || password.isBlank()) {
            showAlert("Validation Error", "Please fill in all required fields.");
            return false;
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            showAlert("Validation Error", "Please enter a valid email address.");
            return false;
        }
        if (password.length() < 8) {
            showAlert("Validation Error", "Password must be at least 8 characters long.");
            return false;
        }
        if (!password.equals(confirmPassword)) {
            showAlert("Validation Error", "Passwords do not match.");
            return false;
        }
        if (!termsCheckBox.isSelected()) {
            showAlert("Validation Error", "You must agree to the Terms & Conditions.");
            return false;
        }
        return true; // All checks passed
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
