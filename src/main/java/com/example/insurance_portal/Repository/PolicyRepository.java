package com.example.insurance_portal.Repository;

import com.example.insurance_portal.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PolicyRepository
        extends JpaRepository<Policy, Long> {

    List<Policy> findByUserUsername(String username);
}