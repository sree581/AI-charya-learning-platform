package com.aicharya.service;

import com.aicharya.model.User;
import com.aicharya.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Creates a new user, hashes their password, and saves them to the database.
     */
    public User registerUser(String username, String email, String password) {
        User newUser = new User();
        newUser.setUserId(UUID.randomUUID().toString());
        newUser.setUsername(username);
        newUser.setEmail(email);
        
        String hashedPassword = passwordEncoder.encode(password);
        newUser.setPasswordHash(hashedPassword);
        System.out.println("--- REGISTRATION ---");
        System.out.println("Registering user with email: " + email);
        System.out.println("Hashed password being saved: " + hashedPassword);
        System.out.println("--------------------");

        newUser.setRegistrationDate(new Date());
        return userRepository.save(newUser);
    }

    /**
     * Checks if the provided login credentials are valid.
     * @param email The email to check.
     * @param rawPassword The plain-text password to check.
     * @return true if login is successful, false otherwise.
     */
    public boolean isValidLogin(String email, String rawPassword) {
        System.out.println("\n--- LOGIN ATTEMPT ---");
        System.out.println("Attempting login for email: " + email);

        User user = userRepository.findByEmail(email);
        
        if (user == null) {
            System.out.println("RESULT: FAILED. No user found with that email address.");
            System.out.println("---------------------");
            return false; 
        }

        System.out.println("User found in database. Checking password...");
        System.out.println("Password from form: " + rawPassword);
        System.out.println("Hashed password from DB: " + user.getPasswordHash());

        boolean isMatch = passwordEncoder.matches(rawPassword, user.getPasswordHash());
        
        if (isMatch) {
            System.out.println("RESULT: SUCCESS. Passwords match.");
        } else {
            System.out.println("RESULT: FAILED. Passwords do NOT match.");
        }
        System.out.println("---------------------");
        
        return isMatch;
    }
}

