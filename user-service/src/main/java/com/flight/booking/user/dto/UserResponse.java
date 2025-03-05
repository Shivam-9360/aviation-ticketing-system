package com.flight.booking.user.dto;

import com.flight.booking.user.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String email;
    private int id;
    private String name;
    private Role role;
    private String password;
}
