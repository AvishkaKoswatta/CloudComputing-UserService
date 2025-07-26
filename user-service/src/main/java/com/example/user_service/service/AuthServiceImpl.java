package com.example.user_service.service;



import com.example.user_service.dto.LoginRequestDto;
import com.example.user_service.dto.LoginResponseDto;
import com.example.user_service.dto.RegisterRequestDto;
import com.example.user_service.dto.UserResponseDto;
import com.example.user_service.entity.User;
import com.example.user_service.exception.EmailAlreadyExistsException;
import com.example.user_service.exception.InvalidCredentialsException;
import com.example.user_service.exception.UserNotFoundException;
import com.example.user_service.repository.UserRepository;
import com.example.user_service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    @Transactional
    public UserResponseDto register(RegisterRequestDto registerRequestDto) {
        // Check if email already exists
        if (userRepository.existsByEmail(registerRequestDto.getEmail())) {
            throw new EmailAlreadyExistsException("Email " + registerRequestDto.getEmail() + " is already registered");
        }

        // Create new user
        User user = new User(
                registerRequestDto.getFirstName(),
                registerRequestDto.getLastName(),
                registerRequestDto.getEmail(),
                passwordEncoder.encode(registerRequestDto.getPassword()),
                registerRequestDto.getPhoneNumber()
        );

        User savedUser = userRepository.save(user);
        return new UserResponseDto(savedUser);
    }

    @Override
    @Transactional
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        // Find user by email
        User user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        // Check if user is active
        if (user.getStatus() != User.UserStatus.ACTIVE) {
            throw new InvalidCredentialsException("Account is not active");
        }

        // Verify password
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        // Update last login time
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        // Generate JWT token
        String token = jwtUtil.generateToken(user);

        return new LoginResponseDto(token, new UserResponseDto(user));
    }

    @Override
    public boolean validateToken(String token) {
        return jwtUtil.validateToken(token);
    }

    @Override
    public Long getUserIdFromToken(String token) {
        return jwtUtil.getUserIdFromToken(token);
    }
}