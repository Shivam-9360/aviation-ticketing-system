package com.flight.booking.auth.service;

import com.flight.booking.auth.feign.UserServiceCommunication;
import com.flight.booking.auth.dto.DTO;
import com.flight.booking.auth.dto.UserResponse;
import com.flight.booking.auth.entity.UserDetailsImpl;
import feign.FeignException;
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
        try {
            DTO<UserResponse> user = communicator.getUserByEmail(username);
            return new UserDetailsImpl(user.getData());
        } catch (FeignException.NotFound ex) {
            throw new UsernameNotFoundException("User with Email " + username + " not found.");
        }
    }
}
