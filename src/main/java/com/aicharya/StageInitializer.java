package com.aicharya;

import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {
    
    private final SceneManager sceneManager;

    // These variables will store the mouse's position when you start dragging
    private double xOffset = 0;
    private double yOffset = 0;

    public StageInitializer(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage = event.getStage();
        
        // This is the key line that removes the default Windows title bar
        stage.initStyle(StageStyle.TRANSPARENT);
        
        // Give the stage to our SceneManager
        sceneManager.setPrimaryStage(stage); 
        
        // Show the very first screen of the application
        sceneManager.showScene("/com/aicharya/view/login-view.fxml", "Login");

        // Add the logic that makes the window draggable
        addDragListeners(stage);
    }
    
    /**
     * This method adds event listeners to the root of whatever scene is currently showing.
     * This allows us to make the entire window draggable.
     * @param stage The main application window (Stage).
     */
    private void addDragListeners(Stage stage) {
        // This listener "watches" for when the scene changes (e.g., from login to dashboard)
        stage.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                Parent root = newScene.getRoot();
                if (root != null) {
                    // This event fires when you press the mouse button down
                    root.setOnMousePressed(event -> {
                        // Record the starting X and Y coordinates of the mouse
                        xOffset = event.getSceneX();
                        yOffset = event.getSceneY();
                    });

                    // This event fires when you drag the mouse while holding the button down
                    root.setOnMouseDragged(event -> {
                        // Move the window's top-left corner to the new mouse position
                        stage.setX(event.getScreenX() - xOffset);
                        stage.setY(event.getScreenY() - yOffset);
                    });
                }
            }
        });
    }
}

