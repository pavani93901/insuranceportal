package com.example.insurance_portal.service;

import com.example.insurance_portal.Repository.ProductRepository;
import com.example.insurance_portal.dto.ProductRequest;
import com.example.insurance_portal.entity.Product;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(
            ProductRepository productRepository) {

        this.productRepository =
                productRepository;
    }

    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    public Product getProduct(Long id) {

        return productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Product not found"));
    }

    public Product createProduct(
            ProductRequest request) {

        Product product = new Product();

        product.setProductName(
                request.productName());

        product.setCategory(
                request.category());

        product.setCoverageAmount(
                request.coverageAmount());

        product.setPremiumAmount(
                request.premiumAmount());

        return productRepository.save(product);
    }

    public Product updateProduct(
            Long id,
            ProductRequest request) {

        Product product = getProduct(id);

        product.setProductName(
                request.productName());

        product.setCategory(
                request.category());

        product.setCoverageAmount(
                request.coverageAmount());

        product.setPremiumAmount(
                request.premiumAmount());

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {

        Product product = getProduct(id);

        productRepository.delete(product);
    }
}