package com.flight.booking.user.service;

import com.flight.booking.user.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(int userId);
    List<User> getAllUsers();
    User updateUser(User user);
    void deleteUserById(int userId);
    void deleteAllUsers();
}
