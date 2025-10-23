package com.aicharya.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class TitleBarController {

    @FXML
    void handleMinimize(ActionEvent event) {
        Stage stage = getStage(event);
        if (stage != null) {
            stage.setIconified(true);
        }
    }

    @FXML
    void handleMaximize(ActionEvent event) {
        Stage stage = getStage(event);
        if (stage != null) {
            stage.setMaximized(!stage.isMaximized());
        }
    }

    @FXML
    void handleClose(ActionEvent event) {
        Platform.exit();
    }
    
    private Stage getStage(ActionEvent event) {
        Node source = (Node) event.getSource();
        return (Stage) source.getScene().getWindow();
    }
}
