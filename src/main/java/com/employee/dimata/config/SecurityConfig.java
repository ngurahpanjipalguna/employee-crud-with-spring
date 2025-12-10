package com.employee.dimata.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public Argon2PasswordEncoder passwordEncoder() {
        return new Argon2PasswordEncoder(
                16,    // panjang salt
                32,    // panjang hash
                1,     // paralelism
                65536, // memory (KB)
                3      // iterasi
        );
    }
}
