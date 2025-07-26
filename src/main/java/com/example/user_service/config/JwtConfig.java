package com.example.user_service.config;


import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    public static final String SECRET_KEY = "mySecretKeymySecretKeymySecretKeymySecretKey";
    public static final long JWT_EXPIRATION = 86400000; // 24 hours
}