package com.aicharya.controller;

import com.aicharya.SceneManager;
import com.aicharya.model.Question;
import com.aicharya.model.Quiz;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class QuizController {

    @Autowired
    private SceneManager sceneManager;

    @FXML private Label quizTitleLabel;
    @FXML private Label questionLabel;
    @FXML private VBox optionsVBox;

    private Quiz currentQuiz;
    private int currentQuestionIndex = 0;
    private ToggleGroup optionsGroup;

    @FXML
    public void initialize() {
        // Load our mock quiz data
        loadMockQuiz();
        // Display the first question
        displayQuestion();
    }
    
    private void loadMockQuiz() {
        Question q1 = new Question("What is the capital of France?", List.of("Berlin", "Madrid", "Paris", "Rome"), 2);
        Question q2 = new Question("Which planet is known as the Red Planet?", List.of("Earth", "Mars", "Jupiter", "Venus"), 1);
        currentQuiz = new Quiz("General Knowledge", List.of(q1, q2));
    }

    private void displayQuestion() {
        Question question = currentQuiz.getQuestions().get(currentQuestionIndex);
        quizTitleLabel.setText(currentQuiz.getTitle());
        questionLabel.setText(question.getQuestionText());

        optionsVBox.getChildren().clear(); // Clear previous options
        optionsGroup = new ToggleGroup();

        for (String optionText : question.getOptions()) {
            RadioButton radioButton = new RadioButton(optionText);
            radioButton.setToggleGroup(optionsGroup);
            optionsVBox.getChildren().add(radioButton);
        }
    }
    
    @FXML
    private void handleSubmitAction() {
        RadioButton selectedRadioButton = (RadioButton) optionsGroup.getSelectedToggle();
        if (selectedRadioButton == null) {
            showAlert("No Selection", "Please select an answer.");
            return;
        }

        int selectedIndex = optionsVBox.getChildren().indexOf(selectedRadioButton);
        if (selectedIndex == currentQuiz.getQuestions().get(currentQuestionIndex).getCorrectOptionIndex()) {
            showAlert("Result", "Correct!");
        } else {
            showAlert("Result", "Incorrect. Try the next question.");
        }
        
        // Move to the next question or end the quiz
        currentQuestionIndex++;
        if (currentQuestionIndex < currentQuiz.getQuestions().size()) {
            displayQuestion();
        } else {
            showAlert("Quiz Finished", "You have completed the quiz!");
            sceneManager.showScene("dashboard-view.fxml", "Dashboard");
        }
    }

    @FXML
private void handleGoBackAction() {
    // This now correctly navigates back to the Course Hub page
    sceneManager.showScene("course-hub-view.fxml", "Course Hub");
}
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}