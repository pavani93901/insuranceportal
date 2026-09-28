package com.example.insurance_portal.controller;

import com.example.insurance_portal.dto.PurchasePolicyRequest;
import com.example.insurance_portal.entity.Policy;
import com.example.insurance_portal.service.PolicyService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {

    private final PolicyService policyService;

    public PolicyController(
            PolicyService policyService) {

        this.policyService = policyService;
    }

    @PostMapping("/purchase")
    public ResponseEntity<Policy> purchasePolicy(
            @Valid
            @RequestBody PurchasePolicyRequest request,
            Authentication authentication) {

        Policy policy =
                policyService.purchasePolicy(
                        request.productId(),
                        authentication.getName());

        return ResponseEntity.status(
                        HttpStatus.CREATED)
                .body(policy);
    }

    @GetMapping("/my")
    public List<Policy> getMyPolicies(
            Authentication authentication) {

        return policyService.getMyPolicies(
                authentication.getName());
    }
}