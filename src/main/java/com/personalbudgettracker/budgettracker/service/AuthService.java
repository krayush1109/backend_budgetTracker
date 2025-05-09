package com.personalbudgettracker.budgettracker.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.personalbudgettracker.budgettracker.config.JwtUtils;
import com.personalbudgettracker.budgettracker.exceptions.UnauthorizedException;
import com.personalbudgettracker.budgettracker.model.AuthRequest;
import com.personalbudgettracker.budgettracker.model.AuthResponse;
import com.personalbudgettracker.budgettracker.model.User;
import com.personalbudgettracker.budgettracker.repository.UserRepo;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String registerUser(User user) {
        if (userRepo.findByEmail(user.getEmail()).isPresent()) {
            return "Error: Email is already in use!";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        userRepo.save(user);

        return "User registered successfully!";
    }

    // public AuthResponse login(AuthRequest authRequest) {
    //     authenticationManager.authenticate(
    //         new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
    //     );

    //     UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
    //     String token = jwtUtils.generateToken(userDetails.getUsername());

    //     return new AuthResponse(token);
    // }

    public AuthResponse login(AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

        User user = userRepo.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
        String token = jwtUtils.generateToken(userDetails.getUsername());

        return new AuthResponse(token, user.getUserId(), user.getUsername()); // Returning more data
    }

    public String updateUser(String email, User updatedUser) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        ;

        if (!user.getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized action!");
        }

        user.setUsername(updatedUser.getUsername());

        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        userRepo.save(user);
        return "User updated successfully!";
    }

    public String deleteUser(String email) {

        Optional<User> optionalUser = userRepo.findByEmail(email);
        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException(email));

        if (!user.getEmail().equals(email)) {
            throw new UnauthorizedException("User is not authorized to delete this account.");
        }

        userRepo.delete(user);
        return "User deleted successfully!";
    }
}

