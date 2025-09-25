package com.aicharya.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "users") // Use a clear table name
@Inheritance(strategy = InheritanceType.JOINED) // This strategy handles the Student-User relationship
public class User {

    @Id
    private String userId; // from -userId: String

    private String username; // from -username: String
    private String passwordHash; // from -passwordHash: String
    private String email; // from -email: String
    private Date registrationDate; // from -registrationDate: Date

    // We can add the login() and updateProfile() methods later in the service layer.
    
    // Getters and Setters for all fields are needed for JPA.
    // (You can auto-generate these in VS Code by right-clicking -> Source Action -> Generate Getters and Setters)
    
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Date getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(Date registrationDate) { this.registrationDate = registrationDate; }
}