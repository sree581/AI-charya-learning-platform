package com.aicharya.service;

import com.aicharya.model.Quiz;
import com.aicharya.repository.QuizRepository; // Import the repository
import org.springframework.beans.factory.annotation.Autowired; // Import Autowired
import org.springframework.stereotype.Service;

@Service
public class QuizService {

    @Autowired // Ask Spring to give us an instance of QuizRepository
    private QuizRepository quizRepository;

    // This method now fetches from the database!
    public Quiz findQuizById(String quizId) {
        // Use the repository to find a quiz by its ID.
        // If not found, it will return null (we can handle that later).
        return quizRepository.findById(quizId).orElse(null);
    }
}