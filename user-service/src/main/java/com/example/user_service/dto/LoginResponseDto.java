package com.example.user_service.dto;


import com.example.user_service.entity.User;

public class LoginResponseDto {
    private String token;
    private String tokenType = "Bearer";
    private UserResponseDto user;

    // Constructors
    public LoginResponseDto() {}

    public LoginResponseDto(String token, UserResponseDto user) {
        this.token = token;
        this.user = user;
    }

    // Getters and Setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getTokenType() { return tokenType; }
    public void setTokenType(String tokenType) { this.tokenType = tokenType; }

    public UserResponseDto getUser() { return user; }
    public void setUser(UserResponseDto user) { this.user = user; }
}