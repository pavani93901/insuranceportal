package com.example.insurance_portal.controller;

import com.example.insurance_portal.dto.ClaimRequest;
import com.example.insurance_portal.entity.Claim;
import com.example.insurance_portal.service.ClaimService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {

    private final ClaimService claimService;

    public ClaimController(
            ClaimService claimService) {

        this.claimService = claimService;
    }

    @PostMapping
    public ResponseEntity<Claim> submitClaim(
            @Valid
            @RequestBody ClaimRequest request,
            Authentication authentication) {

        Claim claim =
                claimService.submitClaim(
                        request,
                        authentication.getName());

        return ResponseEntity.status(
                        HttpStatus.CREATED)
                .body(claim);
    }

    @GetMapping("/my")
    public List<Claim> getMyClaims(
            Authentication authentication) {

        return claimService.getMyClaims(
                authentication.getName());
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Claim> getAllClaims() {

        return claimService.getAllClaims();
    }

    @PutMapping("/{id}/approve")
    @PreAuthorize("hasRole('ADMIN')")
    public Claim approveClaim(
            @PathVariable Long id) {

        return claimService.approveClaim(id);
    }

    @PutMapping("/{id}/reject")
    @PreAuthorize("hasRole('ADMIN')")
    public Claim rejectClaim(
            @PathVariable Long id) {

        return claimService.rejectClaim(id);
    }
}