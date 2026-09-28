package com.example.insurance_portal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(

        @NotBlank
        String productName,

        @NotBlank
        String category,

        @NotNull
        @Positive
        BigDecimal coverageAmount,

        @NotNull
        @Positive
        BigDecimal premiumAmount
) {
}