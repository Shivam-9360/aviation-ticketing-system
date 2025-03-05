package com.flight.booking.user.service;

import com.flight.booking.user.dto.UserRequest;
import com.flight.booking.user.dto.UserResponse;
import com.flight.booking.user.entity.User;
import com.flight.booking.user.exception.NoUsersFoundException;
import com.flight.booking.user.exception.UserAlreadyExistsException;
import com.flight.booking.user.exception.UserNotFoundException;
import com.flight.booking.user.mapper.UserMapper;
import com.flight.booking.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse createUser(UserRequest user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new UserAlreadyExistsException("User with email " + user.getEmail() + " already exists");
        }
        return userMapper.mapToDTO(userRepository.save(userMapper.mapToModel(user)));
    }

    @Override
    public UserResponse getUserByEmail(String emailId) {
        User exisitingUser =  userRepository.findByEmail(emailId)
                .orElseThrow(()-> new UserNotFoundException("User with Email " + emailId + " not found."));
        return userMapper.mapToDTO(exisitingUser);
    }

    @Override
    public UserResponse getUserById(int userId) {
        return userMapper.mapToDTO(userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with Id " + userId + " not found.")));
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users =  userRepository.findAll();
        if(users.isEmpty()){
            throw new NoUsersFoundException("No Users Found");
        }
        return users.stream().map(userMapper::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public UserResponse updateUser(UserRequest user) {
        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new UserNotFoundException("Cannot update. User not found with ID: " + user.getId()));

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setRole(user.getRole());

        return userMapper.mapToDTO(userRepository.save(existingUser));
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
