package com.flight.booking.auth.service;

import com.flight.booking.auth.config.UserServiceCommunication;
import com.flight.booking.auth.dto.DTO;
import com.flight.booking.auth.dto.UserResponse;
import com.flight.booking.auth.entity.UserDetailsImpl;
import com.netflix.discovery.converters.Auto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserServiceCommunication communicator;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DTO<UserResponse> user = communicator.getUserByEmail(username);
        return new UserDetailsImpl(user.getData());
    }
}
