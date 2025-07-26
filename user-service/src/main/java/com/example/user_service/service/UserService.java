package com.example.user_service.service;



import com.example.user_service.dto.UserRequestDto;
import com.example.user_service.dto.UserResponseDto;
import com.example.user_service.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserResponseDto getUserById(Long userId);
    UserResponseDto getUserByEmail(String email);
    UserResponseDto updateUser(Long userId, UserRequestDto userRequestDto);
    UserResponseDto updateUserStatus(Long userId, User.UserStatus status);
    void deleteUser(Long userId);
    Page<UserResponseDto> getAllUsers(Pageable pageable);
    Page<UserResponseDto> searchUsers(String firstName, String lastName, String email,
                                      User.Role role, User.UserStatus status, Pageable pageable);
    UserResponseDto changePassword(Long userId, String currentPassword, String newPassword);
}