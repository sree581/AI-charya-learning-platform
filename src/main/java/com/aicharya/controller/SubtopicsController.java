package com.aicharya.controller;

import com.aicharya.SceneManager;
import com.aicharya.SceneManager.DataReceiver;
import com.aicharya.service.AIPersonalizationEngine;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubtopicsController implements DataReceiver {

    @Autowired
    private SceneManager sceneManager;

    @FXML
    private ListView<String> subtopicsListView;

    private final AIPersonalizationEngine aiEngine = new AIPersonalizationEngine();

    private boolean quizAttempted = false;
    private String selectedSubtopic = "";

    @FXML
public void initialize() {
    // Populate the list with mock subtopic data
    subtopicsListView.setItems(FXCollections.observableArrayList(
        "SUBTOPIC 1: Variables and Data Types",
        "SUBTOPIC 2: Control Flow (Loops and If-Statements)",
        "SUBTOPIC 3: Object-Oriented Programming",
        "SUBTOPIC 4: Exception Handling"
    ));

    // Navigate automatically when a subtopic is selected
    subtopicsListView.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedSubtopic = newValue;
                System.out.println(">>> Subtopic selected: " + newValue);

                // Navigate to SubtopicDetail page and pass selected subtopic
                sceneManager.showSceneWithData("/com/aicharya/view/subtopic-detail-view.fxml",
                                               "Subtopic Details", selectedSubtopic);
            }
        }
    );
}
    @FXML
    private void handleBackButtonAction() {
        sceneManager.showScene("/com/aicharya/view/dashboard-view.fxml", "Dashboard");
    }

    @FXML
    private void openQuiz() {
        if (selectedSubtopic.isEmpty()) {
            showAlert("Please select a subtopic first!");
            return;
        }

        String quizText = aiEngine.generateQuiz(selectedSubtopic);
        sceneManager.showSceneWithData("/com/aicharya/view/quiz-view.fxml", "Quiz", quizText);
        quizAttempted = true;
    }

    @FXML
    private void openWeakAreas() {
        if (!quizAttempted) {
            showAlert("You must attempt at least one quiz to view Weak Areas.");
            return;
        }

        String analysis = aiEngine.analyzePerformance("user_answers_here");
        sceneManager.showSceneWithData("/com/aicharya/view/weak-areas-view.fxml", "Weak Areas", analysis);
    }

    @FXML
    private void openProgress() {
        if (!quizAttempted) {
            showAlert("You must attempt at least one quiz to view Progress Card.");
            return;
        }

        String progress = aiEngine.generateProgressCard("user_performance_summary");
        sceneManager.showSceneWithData("/com/aicharya/view/progress-card-view.fxml", "Progress Card", progress);
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    // ✅ Implemented from DataReceiver
    @Override
    public void setData(Object data) {
        // Currently not used in SubtopicsController, but must be defined
        System.out.println("Received data: " + data);
    }
}