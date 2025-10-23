package com.aicharya.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap; // ✅ Import HashMap
import java.util.Map;     // ✅ Import Map
import java.util.Scanner;
import java.io.IOException;

@Service
public class AIPersonalizationEngine {

    @Value("${openai.api.key}")
    private String openaiApiKey;

    // ✅ Simple in-memory cache: Map<Topic, QuizContent>
    private Map<String, String> quizCache = new HashMap<>();

    // (Other methods like generateRoutine, etc. remain)
    public void generateRoutine() { System.out.println("Generating routine..."); }
    public void analyzeWeakSubjects() { System.out.println("Analyzing weak subjects..."); }
    public void suggestLearningResources() { System.out.println("Suggesting resources..."); }


    public String generateQuiz(String subtopic) {
        // ✅ Check cache first!
        if (quizCache.containsKey(subtopic)) {
            System.out.println(">>> CACHE HIT: Returning cached quiz for topic: " + subtopic);
            return quizCache.get(subtopic);
        }

        // --- If not in cache, call the API ---
        System.out.println(">>> CACHE MISS: Calling AI to generate quiz for topic: " + subtopic);
        String prompt = "Generate 5 multiple choice questions on " + subtopic
                + " with 4 options each and show correct answers clearly.";

        String quizContent = callOpenAI(prompt);

        // ✅ Store successful results in cache (don't cache errors)
        if (quizContent != null && !quizContent.startsWith("Error calling AI API")) {
            quizCache.put(subtopic, quizContent);
        }

        return quizContent;
    }

    public String analyzePerformance(String answers) {
        // Caching might not be suitable here unless the input 'answers' are identical often.
        String prompt = "Given the following quiz results: " + answers
                + " Analyze and list weak areas and suggestions to improve.";
        return callOpenAI(prompt);
    }

    public String generateProgressCard(String summary) {
        // Caching might not be suitable here.
        String prompt = "Create a short progress card based on this performance summary: " + summary;
        return callOpenAI(prompt);
    }

    // (The callOpenAI method remains the same as the updated version)
    private String callOpenAI(String prompt) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL("https://api.openai.com/v1/chat/completions");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + openaiApiKey);
            conn.setDoOutput(true);

            String inputJson = String.format(
                "{ \"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"user\", \"content\": \"%s\"}], \"max_tokens\": 500 }",
                prompt.replace("\"", "\\\"")
            );

            try (OutputStream os = conn.getOutputStream()) {
                os.write(inputJson.getBytes(StandardCharsets.UTF_8));
                os.flush();
            }

            int responseCode = conn.getResponseCode();

            if (responseCode >= 200 && responseCode < 300) {
                try (InputStream is = conn.getInputStream(); Scanner sc = new Scanner(is, StandardCharsets.UTF_8)) {
                    StringBuilder response = new StringBuilder();
                    while (sc.hasNextLine()) { response.append(sc.nextLine()); }

                    String result = response.toString();
                    int idx = result.indexOf("\"content\":\"");
                    if (idx != -1) {
                        int endIdx = result.indexOf("\"", idx + 11);
                        if (endIdx != -1) {
                            return result.substring(idx + 11, endIdx).replace("\\n", "\n").replace("\\\"", "\"").trim();
                        }
                    }
                     System.err.println("Unexpected AI Response format: " + result);
                    return "AI Response format unexpected. See console.";
                }
            } else {
                try (InputStream es = conn.getErrorStream(); Scanner sc = new Scanner(es, StandardCharsets.UTF_8)) {
                    StringBuilder errorResponse = new StringBuilder();
                    while (sc.hasNextLine()) { errorResponse.append(sc.nextLine()); }
                    System.err.println("OpenAI API Error - Status: " + responseCode + ", Response: " + errorResponse.toString());
                    // ✅ Return specific error for rate limiting
                    if (responseCode == 429) {
                         return "Error calling AI API: Rate limit exceeded (Status 429). Please try again later.";
                    }
                    return "Error calling AI API - Status: " + responseCode + ". Check console log.";
                } catch (IOException ioEx) {
                     System.err.println("OpenAI API Error - Status: " + responseCode + ", Could not read error stream.");
                     return "Error calling AI API - Status: " + responseCode + ". Check console log.";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error calling AI API: " + e.getMessage();
        } finally {
             if (conn != null) { conn.disconnect(); }
        }
    }
}