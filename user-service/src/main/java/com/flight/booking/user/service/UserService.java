package com.flight.booking.user.service;

import com.flight.booking.user.dto.UserRequest;
import com.flight.booking.user.dto.UserResponse;
import com.flight.booking.user.entity.User;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest user);
    UserResponse getUserByEmail(String emailId);
    UserResponse getUserById(int userId);
    List<UserResponse> getAllUsers();
    UserResponse updateUser(UserRequest user);
    void deleteUserById(int userId);
    void deleteAllUsers();
    UserResponse updateUserRole(UserRequest user);
}
