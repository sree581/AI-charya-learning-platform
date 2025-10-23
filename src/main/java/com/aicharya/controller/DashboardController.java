package com.aicharya.controller;

import com.aicharya.SceneManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DashboardController {

    @Autowired
    private SceneManager sceneManager;

    @FXML
    private ListView<String> navigationList;

    @FXML
    private ComboBox<String> courseComboBox;

    @FXML
    private HBox coursesContainer;

    @FXML
    public void initialize() {
        
        navigationList.setItems(FXCollections.observableArrayList(
            "NEED HELP?", "FEEDBACK", "LOGOUT"
        ));

        courseComboBox.setItems(FXCollections.observableArrayList(
            "Java Programming", "Data Structures", "Web Development"
        ));

        // ✅ EDITED: Combined the two listeners into one for cleaner code.
        navigationList.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue == null) return;

                switch (newValue) {
                    case "NEED HELP?":
                        // ✅ EDITED: Corrected the file path
                        sceneManager.showScene("/com/aicharya/view/need-help-view.fxml", "Need Help?");
                        break;
                    case "FEEDBACK":
                        // ✅ EDITED: Corrected the file path
                        sceneManager.showScene("/com/aicharya/view/feedback-view.fxml", "Feedback");
                        break;
                    case "LOGOUT":
                        // ✅ EDITED: Corrected the file path
                        sceneManager.showScene("/com/aicharya/view/login-view.fxml", "Aicharya Login");
                        break;
                }
            }
        );
        
        courseComboBox.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue != null) {
                    updateCourseDisplay(newValue);
                }
            }
        );
    }
    
    private void updateCourseDisplay(String courseName) {
        // Clear any old course cards
        coursesContainer.getChildren().clear();

        // Create a new card for the selected course
        VBox courseCard = new VBox();
        courseCard.getStyleClass().add("course-card");
        courseCard.setAlignment(Pos.CENTER);
        
        Label courseLabel = new Label(courseName);
        courseCard.getChildren().add(courseLabel);

        // --- THIS IS THE CRUCIAL PART THAT MAKES THE CARD CLICKABLE ---
        courseCard.setOnMouseClicked(event -> {
            System.out.println(">>> Course card for '" + courseName + "' clicked. Navigating to subtopics.");
            // ✅ EDITED: Corrected the file path
            sceneManager.showScene("/com/aicharya/view/subtopics-view.fxml", "Course Subtopics");
        });
        // -----------------------------------------------------------

        // Add the new, clickable card to the display
        coursesContainer.getChildren().add(courseCard);
    }
}