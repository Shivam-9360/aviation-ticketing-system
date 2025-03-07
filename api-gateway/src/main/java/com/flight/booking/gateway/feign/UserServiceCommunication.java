package com.flight.booking.gateway.feign;

import com.flight.booking.gateway.dto.DTO;
import com.flight.booking.gateway.dto.UserRequest;
import com.flight.booking.gateway.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "USER-SERVICE")
public interface UserServiceCommunication {
    @GetMapping("/api/user/email/{email}")
    public DTO<UserResponse> getUserByEmail(@PathVariable("email") String email);
}
