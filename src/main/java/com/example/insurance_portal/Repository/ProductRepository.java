package com.example.insurance_portal.Repository;

import com.example.insurance_portal.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository
        extends JpaRepository<Product, Long> {
}