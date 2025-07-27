package com.carservice.cardemo.controller;

import com.carservice.cardemo.config.JwtUtil;
import com.carservice.cardemo.dto.AuthRequest;
import com.carservice.cardemo.dto.AuthResponse;
import com.carservice.cardemo.model.User;
import com.carservice.cardemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public AuthResponse authenticate(@RequestBody AuthRequest request) {
        // Authenticate username and password
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // Retrieve user to access role
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Generate JWT token
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());

        // Return token in a response DTO
        return new AuthResponse(token);
    }
}
