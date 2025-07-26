package com.example.user_service.service;



import com.example.user_service.dto.LoginRequestDto;
import com.example.user_service.dto.LoginResponseDto;
import com.example.user_service.dto.RegisterRequestDto;
import com.example.user_service.dto.UserResponseDto;

public interface AuthService {
    UserResponseDto register(RegisterRequestDto registerRequestDto);
    LoginResponseDto login(LoginRequestDto loginRequestDto);
    boolean validateToken(String token);
    Long getUserIdFromToken(String token);
}