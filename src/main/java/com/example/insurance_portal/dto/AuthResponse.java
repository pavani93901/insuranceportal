package com.example.insurance_portal.dto;

public record AuthResponse(
        String token,
        String type,
        String role
) {

    public AuthResponse(String token, String role) {
        this(token, "Bearer", role);
    }
}