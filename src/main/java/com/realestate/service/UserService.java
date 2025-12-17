package com.realestate.service;

import com.realestate.model.User;
import com.realestate.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    // REGISTER USER
    public User register(User user) {

        if (userRepo.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already registered!");
        }

        // Assign default role if missing
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("ROLE_USER");
        }

        return userRepo.save(user);
    }

    // LOGIN USER
    public User login(String email, String password) {

        Optional<User> user = userRepo.findByEmailAndPassword(email, password);

        return user.orElse(null); // null means invalid credentials
    }

    // FIND USER BY ID
    public User findById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}

