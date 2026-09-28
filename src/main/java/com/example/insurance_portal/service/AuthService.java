package com.example.insurance_portal.service;

import com.example.insurance_portal.Repository.UserRepository;
import com.example.insurance_portal.dto.AuthResponse;
import com.example.insurance_portal.dto.LoginRequest;
import com.example.insurance_portal.dto.RegisterRequest;
import com.example.insurance_portal.entity.Role;
import com.example.insurance_portal.entity.User;
import com.example.insurance_portal.security.JwtService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public AuthResponse register(
            RegisterRequest request) {

        if (userRepository.existsByUsername(
                request.username())) {

            throw new RuntimeException(
                    "Username already exists");
        }

        User user = new User();

        user.setUsername(
                request.username());

        user.setPassword(
                passwordEncoder.encode(
                        request.password()));

        user.setRole(Role.CUSTOMER);

        userRepository.save(user);

        String token =
                jwtService.generateToken(
                        user.getUsername(),
                        user.getRole().name());

        return new AuthResponse(
                token,
                user.getRole().name());
    }

    public AuthResponse login(
            LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()));

        User user =
                userRepository.findByUsername(
                                request.username())
                        .orElseThrow();

        String token =
                jwtService.generateToken(
                        user.getUsername(),
                        user.getRole().name());

        return new AuthResponse(
                token,
                user.getRole().name());
    }
}