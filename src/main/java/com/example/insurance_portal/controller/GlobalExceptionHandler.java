package com.example.insurance_portal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            IllegalArgumentException.class)
    public ResponseEntity<Map<String,Object>>
    handleIllegalArgument(
            IllegalArgumentException ex) {

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                ex.getMessage());
    }

    @ExceptionHandler(
            BadCredentialsException.class)
    public ResponseEntity<Map<String,Object>>
    handleBadCredentials(
            BadCredentialsException ex) {

        return buildResponse(
                HttpStatus.UNAUTHORIZED,
                "Invalid username or password");
    }

    @ExceptionHandler(
            MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>>
    handleValidation(
            MethodArgumentNotValidException ex) {

        String message =
                ex.getBindingResult()
                        .getFieldErrors()
                        .get(0)
                        .getDefaultMessage();

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                message);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>>
    handleException(
            Exception ex) {

        return buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage());
    }

    private ResponseEntity<Map<String,Object>>
    buildResponse(
            HttpStatus status,
            String message) {

        Map<String,Object> response =
                new HashMap<>();

        response.put(
                "timestamp",
                Instant.now());

        response.put(
                "status",
                status.value());

        response.put(
                "message",
                message);

        return ResponseEntity
                .status(status)
                .body(response);
    }
}