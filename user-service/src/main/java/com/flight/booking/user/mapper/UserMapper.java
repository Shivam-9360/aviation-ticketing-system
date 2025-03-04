package com.flight.booking.user.mapper;

import com.flight.booking.user.dto.UserRequest;
import com.flight.booking.user.dto.UserResponse;
import com.flight.booking.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserMapper {
    public UserResponse mapToDTO(User user){
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole())
                .build();
    }
    public User mapToModel(UserRequest user){
        return User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole())
                .password(user.getPassword())
                .build();
    }
}
