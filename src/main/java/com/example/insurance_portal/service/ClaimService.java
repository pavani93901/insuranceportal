package com.example.insurance_portal.service;

import com.example.insurance_portal.Repository.ClaimRepository;
import com.example.insurance_portal.dto.ClaimRequest;
import com.example.insurance_portal.entity.Claim;
import com.example.insurance_portal.entity.ClaimStatus;
import com.example.insurance_portal.entity.Policy;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClaimService {

    private final ClaimRepository claimRepository;
    private final PolicyService policyService;

    public ClaimService(
            ClaimRepository claimRepository,
            PolicyService policyService) {

        this.claimRepository = claimRepository;
        this.policyService = policyService;
    }

    public Claim submitClaim(
            ClaimRequest request,
            String username) {

        List<Policy> policies =
                policyService.getMyPolicies(username);

        Policy selectedPolicy =
                policies.stream()
                        .filter(policy ->
                                policy.getId()
                                        .equals(request.policyId()))
                        .findFirst()
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Policy not found"));

        Claim claim = new Claim();

        claim.setClaimNumber(
                "CLM-" +
                        UUID.randomUUID()
                                .toString()
                                .substring(0, 8)
                                .toUpperCase());

        claim.setPolicy(selectedPolicy);

        claim.setIncidentDate(
                request.incidentDate());

        claim.setDescription(
                request.description());

        claim.setStatus(
                ClaimStatus.SUBMITTED);

        return claimRepository.save(claim);
    }

    public List<Claim> getMyClaims(
            String username) {

        return claimRepository
                .findByPolicyUserUsername(
                        username);
    }

    public List<Claim> getAllClaims() {

        return claimRepository.findAll();
    }

    public Claim approveClaim(Long claimId) {

        Claim claim =
                claimRepository.findById(claimId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Claim not found"));

        claim.setStatus(
                ClaimStatus.APPROVED);

        return claimRepository.save(claim);
    }

    public Claim rejectClaim(Long claimId) {

        Claim claim =
                claimRepository.findById(claimId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Claim not found"));

        claim.setStatus(
                ClaimStatus.REJECTED);

        return claimRepository.save(claim);
    }
}