package com.example.user_service.controller;


import com.example.user_service.dto.LoginRequestDto;
import com.example.user_service.dto.LoginResponseDto;
import com.example.user_service.dto.RegisterRequestDto;
import com.example.user_service.dto.UserResponseDto;
import com.example.user_service.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
        UserResponseDto user = authService.register(registerRequestDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto loginResponse = authService.login(loginRequestDto);
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/validate")
    public ResponseEntity<Boolean> validateToken(@RequestParam String token) {
        boolean isValid = authService.validateToken(token);
        return ResponseEntity.ok(isValid);
    }

    @GetMapping("/user-from-token")
    public ResponseEntity<Long> getUserFromToken(@RequestParam String token) {
        Long userId = authService.getUserIdFromToken(token);
        return ResponseEntity.ok(userId);
    }
}