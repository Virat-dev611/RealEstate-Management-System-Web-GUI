package com.realestate.repository;

import com.realestate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Find a user by email
    Optional<User> findByEmail(String email);

    // Check if email is already taken (for registration)
    boolean existsByEmail(String email);

    // Find a user by email + password (used for login)
    Optional<User> findByEmailAndPassword(String email, String password);
}
