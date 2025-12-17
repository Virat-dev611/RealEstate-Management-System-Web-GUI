package com.realestate.controller;

import com.realestate.model.User;
import com.realestate.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    private final UserRepository userRepo;

    public HomeController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping({"/", "/index", "/home"})
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        return userRepo.findByEmailAndPassword(email, password)
                .map(user -> {
                    // store whole user object in session (used by Thymeleaf)
                    session.setAttribute("user", user);
                    // optional: store a simple message
                    model.addAttribute("success", "Logged in successfully. Role: " + user.getRole());
                    System.out.println("Logged-in user role: " + user.getRole());
                    return "redirect:/";
                })
                .orElseGet(() -> {
                    model.addAttribute("error", "Invalid credentials");
                    return "login";
                });
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model, HttpSession session) {
        // basic validation
        if (user.getEmail() == null || user.getPassword() == null) {
            model.addAttribute("error", "Email and password are required");
            return "register";
        }
        if (userRepo.existsByEmail(user.getEmail())) {
            model.addAttribute("error", "Email already registered");
            return "register";
        }
        // default role if not set
        if (user.getRole() == null || user.getRole().isBlank()) {
            user.setRole("ROLE_USER");
        }
        User saved = userRepo.save(user);
        session.setAttribute("user", saved);
        model.addAttribute("success", "Registered and logged in");
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
