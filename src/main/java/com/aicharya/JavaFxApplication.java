package com.aicharya;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class JavaFxApplication extends Application {

    private ConfigurableApplicationContext applicationContext;
    private SceneManager sceneManager;

    @Override
    public void init() {
        // Initialize the Spring Boot application
        applicationContext = new SpringApplicationBuilder(AicharyaApplication.class).run();
        // Get SceneManager from Spring context
        sceneManager = applicationContext.getBean(SceneManager.class);
    }

    @Override
    public void start(Stage stage) {
        // Set primary stage with full window and buttons
        sceneManager.setPrimaryStage(stage);

        // Show the login scene first
        sceneManager.showScene("/com/aicharya/view/login-view.fxml", "Login");

        // Maximize the window to fit the screen
        stage.setMaximized(true);
    }

    @Override
    public void stop() {
        // Close Spring context and exit JavaFX
        applicationContext.close();
        Platform.exit();
    }
}
