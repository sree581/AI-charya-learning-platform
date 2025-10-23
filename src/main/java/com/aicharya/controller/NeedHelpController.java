package com.aicharya.controller;

import com.aicharya.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NeedHelpController {

    @Autowired
    private SceneManager sceneManager;

    @FXML
    void handleBackButtonAction(ActionEvent event) {
        sceneManager.showScene("/com/aicharya/view/dashboard-view.fxml", "Dahboard");
    }
}