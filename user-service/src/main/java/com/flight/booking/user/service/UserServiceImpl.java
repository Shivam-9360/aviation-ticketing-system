package com.flight.booking.user.service;

import com.flight.booking.user.entity.User;
import com.flight.booking.user.exception.NoUsersFoundException;
import com.flight.booking.user.exception.UserAlreadyExistsException;
import com.flight.booking.user.exception.UserNotFoundException;
import com.flight.booking.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new UserAlreadyExistsException("User with email " + user.getEmail() + " already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public User getUserById(int userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with Id " + userId + " not found."));
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users =  userRepository.findAll();
        if(users.isEmpty()){
            throw new NoUsersFoundException("No Users Found");
        }
        return users;
    }

    @Override
    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new UserNotFoundException("Cannot update. User not found with ID: " + user.getId()));

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setRole(user.getRole());

        return userRepository.save(existingUser);
    }


    @Override
    public void deleteUserById(int userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("Cannot delete. User not found with ID: " + userId);
        }
        userRepository.deleteById(userId);
    }

    @Override
    public void deleteAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new NoUsersFoundException("No users to delete.");
        }
        userRepository.deleteAll();
    }
}
