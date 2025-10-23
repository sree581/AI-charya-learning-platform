package com.aicharya.controller;

import com.aicharya.SceneManager;
import com.aicharya.SceneManager.DataReceiver;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubtopicDetailsController implements DataReceiver {

    @Autowired
    private SceneManager sceneManager;

    @FXML
    private Label subtopicTitle;

    private String subtopicName;

    @Override
    public void setData(Object data) {
        if (data instanceof String) {
            subtopicName = (String) data;
            subtopicTitle.setText(subtopicName);
        }
    }

    @FXML
    private void openQuiz() {
        sceneManager.showSceneWithData("/com/aicharya/view/quiz-view.fxml", "Quiz", subtopicName);
    }

    @FXML
    private void openWeakAreas() {
        sceneManager.showSceneWithData("/com/aicharya/view/weak-areas-view.fxml", "Weak Areas", subtopicName);
    }

    @FXML
    private void openProgress() {
        sceneManager.showSceneWithData("/com/aicharya/view/progress-card-view.fxml", "Progress Card", subtopicName);
    }

    @FXML
    private void backToSubtopics() {
        sceneManager.showScene("/com/aicharya/view/subtopic-view.fxml", "Subtopics");
    }
}