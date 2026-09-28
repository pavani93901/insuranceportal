package com.example.insurance_portal.Repository;

import com.example.insurance_portal.entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClaimRepository
        extends JpaRepository<Claim, Long> {

    List<Claim> findByPolicyUserUsername(String username);
}