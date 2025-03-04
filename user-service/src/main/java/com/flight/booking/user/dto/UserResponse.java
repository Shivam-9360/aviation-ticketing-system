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
    String email;
    int id;
    String name;
    Role role;
}
