package com.stockapp.stockapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*") // Allow frontend access
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        // Dummy login validation (Replace with actual database check)
        if ("admin@example.com".equals(email) && "password".equals(password)) {
            return ResponseEntity.ok("Login Successful!");
        } else {
            return ResponseEntity.status(401).body("Invalid Credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password) {
        
        // TODO: Add logic to save user to database
        System.out.println("User registered: " + name + ", " + email);
        
        return ResponseEntity.ok("User Registered Successfully!");
    }
}
