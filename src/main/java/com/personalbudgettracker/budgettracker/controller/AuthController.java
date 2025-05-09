package com.personalbudgettracker.budgettracker.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.personalbudgettracker.budgettracker.config.JwtUtils;
import com.personalbudgettracker.budgettracker.model.AuthRequest;
import com.personalbudgettracker.budgettracker.model.AuthResponse;
import com.personalbudgettracker.budgettracker.model.User;
import com.personalbudgettracker.budgettracker.repository.UserRepo;
import com.personalbudgettracker.budgettracker.service.AuthService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        String response = authService.registerUser(user);
        return response.startsWith("Error") ? ResponseEntity.badRequest().body(response) : ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.login(authRequest));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(Authentication authentication, @RequestBody User updatedUser) {
        String email = authentication.getName(); 
        return ResponseEntity.status(200).body(authService.updateUser(email, updatedUser));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(Authentication authentication) {
        String email = authentication.getName(); 
        return ResponseEntity.ok(authService.deleteUser(email));
    }
}

