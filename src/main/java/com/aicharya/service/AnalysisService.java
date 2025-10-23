package com.aicharya.service;

import com.aicharya.model.Question;
import com.aicharya.model.Quiz;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalysisService {

    // You can later add a Gemini call here to summarize weak topics.
    // For now, we will just identify them.

    /**
     * Identifies the topics of questions the user answered incorrectly.
     * @param quiz The quiz that was taken.
     * @param userAnswers A list of the user's answer indices.
     * @return A list of strings representing the weak topics.
     */
    public String identifyWeakTopics(Quiz quiz, List<Integer> userAnswers) {
        StringBuilder weakTopics = new StringBuilder();
        List<Question> questions = quiz.getQuestions();

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            int correctAnswerIndex = question.getCorrectOptionIndex();
            int userAnswerIndex = userAnswers.get(i);

            if (userAnswerIndex != correctAnswerIndex) {
                // For a real app, you'd have topics associated with each question.
                // For now, we'll just use the question text as a proxy for the topic.
                weakTopics.append(question.getQuestionText()).append("\n");
            }
        }
        
        if (weakTopics.length() == 0) {
            return "No specific weak areas identified. Great job!";
        }
        
        // Here you could make another Gemini call to summarize these topics nicely.
        // For example: "Based on these missed questions, you should review: ..."
        return "Areas to review:\n" + weakTopics.toString();
    }

    /**
     * Calculates the user's score as a percentage.
     * @param quiz The quiz that was taken.
     * @param userAnswers A list of the user's answer indices.
     * @return The final score (e.g., 75.0).
     */
    public double calculateScore(Quiz quiz, List<Integer> userAnswers) {
        double correctCount = 0;
        List<Question> questions = quiz.getQuestions();

        for (int i = 0; i < questions.size(); i++) {
            if (userAnswers.get(i) == questions.get(i).getCorrectOptionIndex()) {
                correctCount++;
            }
        }
        return (correctCount / questions.size()) * 100.0;
    }
}