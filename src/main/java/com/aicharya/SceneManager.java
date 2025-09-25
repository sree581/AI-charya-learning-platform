package com.aicharya;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SceneManager {

    private Stage primaryStage;
    private final ApplicationContext springContext;

    public SceneManager(ApplicationContext springContext) {
        this.springContext = springContext;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showScene(String fxmlFile, String title) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/" + fxmlFile));
            fxmlLoader.setControllerFactory(springContext::getBean);
            Parent parent = fxmlLoader.load();
            
            Scene scene = new Scene(parent);

            // --- THIS IS THE NEW, IMPORTANT LINE ---
            // It programmatically adds the stylesheet to every scene we create.
            scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
            // ------------------------------------

            primaryStage.setScene(scene);
            primaryStage.setTitle(title);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}