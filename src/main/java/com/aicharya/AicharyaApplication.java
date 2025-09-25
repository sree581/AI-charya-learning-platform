package com.aicharya;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AicharyaApplication {

	public static void main(String[] args) {
		// This launches the JavaFxApplication, which in turn starts Spring Boot
		Application.launch(JavaFxApplication.class, args);
	}

	// This @Bean for password encoding is still needed, so we keep it.
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    // The old CommandLineRunner that was here has been removed.
}