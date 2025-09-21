package com.aicharya;

import com.aicharya.model.Question;
import com.aicharya.model.Quiz;
import com.aicharya.repository.QuizRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AicharyaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AicharyaApplication.class, args);
	}

	// This @Bean will run once on application startup
	@Bean
	CommandLineRunner initDatabase(QuizRepository repository) {
		return args -> {
			// Create some questions
			Question q1 = new Question("q1_db", "Which planet is known as the Red Planet?", List.of("Earth", "Mars", "Jupiter"), 1);
			Question q2 = new Question("q2_db", "What is the largest mammal?", List.of("Elephant", "Blue Whale", "Giraffe"), 1);

			// Create a quiz with these questions
			Quiz scienceQuiz = new Quiz("sci01", "Science 101", "General Science", List.of(q1, q2));

			// Save the quiz to the H2 database
			repository.save(scienceQuiz);
			System.out.println(">>> Sample Quiz Saved to Database! <<<");
		};
	}
}