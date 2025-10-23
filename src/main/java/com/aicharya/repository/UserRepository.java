package com.aicharya.repository;

import com.aicharya.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    
    // This is a "query method". Spring Data JPA will automatically
    // create the code to find a user by their email address.
    User findByEmail(String email);
}
