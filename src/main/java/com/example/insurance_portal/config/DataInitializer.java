package com.example.insurance_portal.config;

import com.example.insurance_portal.Repository.UserRepository;
import com.example.insurance_portal.entity.Role;
import com.example.insurance_portal.entity.User;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initUsers(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            if (!userRepository.existsByUsername("admin")) {

                User admin = new User();

                admin.setUsername("admin");

                admin.setPassword(
                        passwordEncoder.encode("Admin@123"));

                admin.setRole(Role.ADMIN);

                userRepository.save(admin);

                System.out.println(
                        "Admin user created successfully");
            }
        };
    }
}