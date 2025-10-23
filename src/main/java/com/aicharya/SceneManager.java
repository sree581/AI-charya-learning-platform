package com.aicharya;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SceneManager {

    private Stage primaryStage;
    private final ApplicationContext springContext;

    public SceneManager(ApplicationContext springContext) {
        this.springContext = springContext;
    }

    // ✅ Called once from your main Application class
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setResizable(true);
        // ❌ Do not call initStyle() here again; it causes the IllegalStateException
    }

    // ✅ Universal scene loader with fullscreen consistency
    public void showScene(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            loader.setControllerFactory(springContext::getBean);

            Parent root = loader.load();

            Stage stage = primaryStage;

            // ✅ Preserve old window state before switching scene
            boolean wasMaximized = stage.isMaximized();
            double oldWidth = stage.getWidth();
            double oldHeight = stage.getHeight();

            // ✅ Apply new scene
            stage.setTitle(title);
            stage.setScene(new Scene(root));

            // ✅ Restore or maximize
            if (wasMaximized || oldWidth == 0 || oldHeight == 0) {
                stage.setMaximized(true);
            } else {
                stage.setWidth(oldWidth);
                stage.setHeight(oldHeight);
                stage.centerOnScreen();
            }

            // ✅ Always show (safe even if already visible)
            if (!stage.isShowing()) {
                stage.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ ADD THIS METHOD (new) — used for passing data between scenes
    
    public void showSceneWithData(String fxmlPath, String title, Object data) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        loader.setControllerFactory(springContext::getBean);
        Parent root = loader.load();

        // Pass data if controller implements DataReceiver
        Object controller = loader.getController();
        if (controller instanceof DataReceiver receiver) {
            receiver.setData(data);
        }

        Stage stage = primaryStage;
        boolean wasMaximized = stage.isMaximized();
        double oldWidth = stage.getWidth();
        double oldHeight = stage.getHeight();

        stage.setTitle(title);
        stage.setScene(new Scene(root));

        if (wasMaximized || oldWidth == 0 || oldHeight == 0) {
            stage.setMaximized(true);
        } else {
            stage.setWidth(oldWidth);
            stage.setHeight(oldHeight);
            stage.centerOnScreen();
        }

        if (!stage.isShowing()) stage.show();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    // ✅ ADD THIS INTERFACE (new)
    public interface DataReceiver {
        void setData(Object data);
    }
}