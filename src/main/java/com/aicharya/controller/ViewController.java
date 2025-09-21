package com.aicharya.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {

    // Serves the public home page
    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    // Serves the login page
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // Serves the registration page
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }
    
    // Serves the user dashboard page
    @GetMapping("/dashboard")
    public String dashboardPage() {
        return "dashboard";
    }

    // After "login", redirect to the dashboard. No password check needed.
    @PostMapping("/login")
    public String handleLogin() {
        System.out.println(">>> User clicked login. Redirecting to dashboard.");
        return "redirect:/dashboard";
    }

    // After "register", redirect to the login page. No user creation needed.
    @PostMapping("/register")
    public String handleRegistration() {
        System.out.println(">>> User clicked register. Redirecting to login page.");
        return "redirect:/login";
    }


@GetMapping("/forgot-password")
    public String forgotPasswordPage() {
        return "forgot-password";
    }

    // Handles the form submission from the forgot password page
   @PostMapping("/forgot-password")
public String handleForgotPassword(@RequestParam String email, 
                                   @RequestParam String newPassword, 
                                   @RequestParam String confirmPassword) {
    // In our mock backend, we'll just print to confirm we received the data
    System.out.println(">>> Password change requested for email: " + email);
    System.out.println(">>> New password received: " + newPassword);
    
    // After submission, redirect the user back to the login page
    return "redirect:/login";
}
}
