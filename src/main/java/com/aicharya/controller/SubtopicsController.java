package com.aicharya.controller;

import com.aicharya.SceneManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubtopicsController {

    @Autowired
    private SceneManager sceneManager;

    @FXML
    private ListView<String> subtopicsListView;

    @FXML
    public void initialize() {
        // Populate the list with mock subtopic data
        subtopicsListView.setItems(FXCollections.observableArrayList(
            "SUBTOPIC 1: Variables and Data Types",
            "SUBTOPIC 2: Control Flow (Loops and If-Statements)",
            "SUBTOPIC 3: Object-Oriented Programming",
            "SUBTOPIC 4: Exception Handling"
        ));

        // Add a listener to handle clicks on a subtopic
        subtopicsListView.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue != null) {
                    System.out.println(">>> Subtopic selected: " + newValue);
                    // Navigate to the Course Hub for the selected subtopic
                    sceneManager.showScene("course-hub-view.fxml", "Course Hub");
                }
            }
        );
    }

    @FXML
    private void handleBackButtonAction() {
        sceneManager.showScene("dashboard-view.fxml", "Dashboard");
    }
}