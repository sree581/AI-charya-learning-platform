package com.aicharya.controller;

import com.aicharya.SceneManager;
import com.aicharya.SceneManager.DataReceiver;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProgressCardController implements DataReceiver {

    @Autowired
    private SceneManager sceneManager;

    @FXML
    private TextArea progressTextArea;

    // Implement exactly the interface method
    @Override
    public void setData(Object data) {
        if (data != null) {
            progressTextArea.setText(data.toString());
        }
    }

    @FXML
    private void handleBackButtonAction() {
        sceneManager.showScene("/com/aicharya/view/subtopic-view.fxml", "Subtopics");
    }
}