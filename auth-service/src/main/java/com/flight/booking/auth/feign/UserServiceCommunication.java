package com.flight.booking.auth.feign;

import com.flight.booking.auth.dto.DTO;
import com.flight.booking.auth.dto.UserRequest;
import com.flight.booking.auth.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "USER-SERVICE", url = "${user.service.url:}")
public interface UserServiceCommunication {
    @GetMapping("/api/user/email/{email}")
    public DTO<UserResponse> getUserByEmail(@PathVariable("email") String email);

    @PostMapping("/api/user")
    public DTO<UserResponse> createUser(@RequestBody UserRequest userRequest);
}
