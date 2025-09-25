package com.aicharya.controller;

import com.aicharya.SceneManager;
import javafx.collections.FXCollections; // Import this
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.regex.Pattern;
import java.util.List; // Import this

@Component
public class RegisterController {
    
    // ... (The EMAIL_PATTERN and other fields are the same)
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Autowired
    private SceneManager sceneManager;

    @FXML private TextField nameField;
    @FXML private ComboBox<String> eduLevelComboBox;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private CheckBox termsCheckBox;
    @FXML private Button registerButton;

    // ADD THIS NEW METHOD
    @FXML
    public void initialize() {
        // Create the list of education levels
        List<String> levels = List.of(
            "Class 1", "Class 2", "Class 3", "Class 4", "Class 5", "Class 6",
            "Class 7", "Class 8", "Class 9", "Class 10", "Class 11", "Class 12",
            "Undergraduate", "Postgraduate"
        );
        // Set the items in the ComboBox
        eduLevelComboBox.setItems(FXCollections.observableArrayList(levels));
    }

    @FXML
    void handleRegisterButtonAction(ActionEvent event) {
        // ... (This method remains exactly the same as before)
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (name.isBlank() || email.isBlank() || password.isBlank()) {
            showAlert("Validation Error", "Please fill in all required fields.");
            return;
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            showAlert("Validation Error", "Please enter a valid email address.");
            return;
        }
        if (password.length() < 8) {
            showAlert("Validation Error", "Password must be at least 8 characters long.");
            return;
        }
        if (!password.equals(confirmPassword)) {
            showAlert("Validation Error", "Passwords do not match.");
            return;
        }
        if (!termsCheckBox.isSelected()) {
            showAlert("Validation Error", "You must agree to the Terms & Conditions.");
            return;
        }

        System.out.println(">>> Validation successful! Registering user: " + name);
        sceneManager.showScene("login-view.fxml", "Aicharya Login");
    }
    @FXML
void handleLoginLinkAction(ActionEvent event) {
    sceneManager.showScene("login-view.fxml", "Aicharya Login");
}


    private void showAlert(String title, String message) {
        // ... (This method remains exactly the same as before)
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}