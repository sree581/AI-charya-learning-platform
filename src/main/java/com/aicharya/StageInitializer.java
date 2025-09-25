package com.aicharya;

import javafx.stage.Stage;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {
    
    private final SceneManager sceneManager;

    public StageInitializer(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage = event.getStage();
        sceneManager.setPrimaryStage(stage); // Give the stage to the manager
        sceneManager.showScene("login-view.fxml", "Aicharya Login"); // Show the first scene
    }
}