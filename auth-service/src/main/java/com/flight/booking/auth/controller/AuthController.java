package com.flight.booking.auth.controller;

import com.flight.booking.auth.feign.UserServiceCommunication;
import com.flight.booking.auth.dto.*;
import com.flight.booking.auth.exception.CredentialsNotValidException;
import com.flight.booking.auth.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserServiceCommunication communicator;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<DTO<AuthResponse>> authenticateAndGetToken(@RequestBody AuthRequest authRequest) throws Exception {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            DTO<UserResponse> user = communicator.getUserByEmail(authRequest.getEmail());
            final String token  = jwtService.generateToken(user.getData().getEmail());
            return ResponseEntity.ok()
                    .header("Authorization", "Bearer " + token)
                    .body(DTO.<AuthResponse>builder()
                            .success(true)
                            .message("Logged In Successfully")
                            .data(AuthResponse.builder()
                                    .email(user.getData().getEmail())
                                    .name(user.getData().getName())
                                    .role(user.getData().getRole())
                                    .build())
                            .build());
        } else {
            throw new CredentialsNotValidException("Invalid user request!");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<DTO<AuthResponse>> registerAndGetToken(@RequestBody UserRequest userRequest) throws NoSuchAlgorithmException, InvalidKeySpecException {
        userRequest.setPassword(new BCryptPasswordEncoder().encode(userRequest.getPassword()));
        DTO<UserResponse> userResponse = communicator.createUser(userRequest);

        if (userResponse != null && userResponse.isSuccess()) {
            final String token  = jwtService.generateToken(userResponse.getData().getEmail());
            return ResponseEntity.ok()
                    .header("Authorization", "Bearer " + token)
                    .body(DTO.<AuthResponse>builder()
                            .success(true)
                            .message("Registered Successfully")
                            .data(AuthResponse.builder()
                                    .email(userResponse.getData().getName())
                                    .name(userResponse.getData().getName())
                                    .role(userResponse.getData().getRole())
                                    .build())
                            .build());
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
