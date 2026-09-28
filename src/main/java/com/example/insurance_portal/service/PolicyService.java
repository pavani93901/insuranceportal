package com.example.insurance_portal.service;

import com.example.insurance_portal.Repository.PolicyRepository;
import com.example.insurance_portal.Repository.ProductRepository;
import com.example.insurance_portal.Repository.UserRepository;

import com.example.insurance_portal.entity.Policy;
import com.example.insurance_portal.entity.Product;
import com.example.insurance_portal.entity.User;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PolicyService {

    private final PolicyRepository policyRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public PolicyService(
            PolicyRepository policyRepository,
            ProductRepository productRepository,
            UserRepository userRepository) {

        this.policyRepository =
                policyRepository;

        this.productRepository =
                productRepository;

        this.userRepository =
                userRepository;
    }

    public Policy purchasePolicy(
            Long productId,
            String username) {

        User user =
                userRepository.findByUsername(
                                username)
                        .orElseThrow();

        Product product =
                productRepository.findById(
                                productId)
                        .orElseThrow();

        Policy policy = new Policy();

        policy.setPolicyNumber(
                "POL-" +
                        UUID.randomUUID()
                                .toString()
                                .substring(0, 8));

        policy.setUser(user);

        policy.setProduct(product);

        policy.setStartDate(
                LocalDate.now());

        policy.setEndDate(
                LocalDate.now()
                        .plusYears(1));

        return policyRepository.save(policy);
    }

    public List<Policy> getMyPolicies(
            String username) {

        return policyRepository
                .findByUserUsername(
                        username);
    }
}