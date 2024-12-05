package com.example.gym.controller;

import com.example.gym.exception.ResourceNotFound;
import com.example.gym.model.User;
import com.example.gym.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        // Check if the email is already taken
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is already taken.");
        }

        // Encrypt password
        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encryptedPassword);

        // Save the user to the database
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        Optional<User> user = Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new ResourceNotFound("User not found wth ID" + id)));
        return ResponseEntity.ok(user.get());
    }
}
