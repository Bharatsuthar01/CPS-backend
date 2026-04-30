package com.bank.cps.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import jakarta.annotation.PostConstruct;

@Configuration
public class ConfigValidation {

    @Value("${JWT_SECRET:}")
    private String jwtSecret;

    @Value("${spring.data.mongodb.uri:}")
    private String mongoUri;

    @PostConstruct
    public void validateConfig() {
        if (!StringUtils.hasText(jwtSecret)) {
            throw new IllegalStateException("JWT_SECRET must be non-blank at startup.");
        }
        if (!StringUtils.hasText(mongoUri)) {
            throw new IllegalStateException("MONGODB_URI (spring.data.mongodb.uri) must be non-blank at startup.");
        }
    }
}
