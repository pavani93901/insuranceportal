package com.example.insurance_portal.dto;

import jakarta.validation.constraints.NotNull;

public record PurchasePolicyRequest(

        @NotNull
        Long productId
) {
}