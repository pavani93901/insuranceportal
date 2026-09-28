package com.example.insurance_portal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record ClaimRequest(

        @NotNull
        Long policyId,

        @NotNull
        @PastOrPresent
        LocalDate incidentDate,

        @NotBlank
        String description
) {
}