package com.aicharya;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class JavaFxApplication extends Application {

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        // Initialize the Spring Boot application
        applicationContext = new SpringApplicationBuilder(AicharyaApplication.class).run();
    }

    @Override
    public void start(Stage stage) {
        // When the JavaFX app starts, tell Spring Boot that it is ready
        applicationContext.publishEvent(new StageReadyEvent(stage));
    }

    @Override
    public void stop() {
        // When the JavaFX app closes, shut down the Spring Boot application
        applicationContext.close();
        Platform.exit();
    }
}