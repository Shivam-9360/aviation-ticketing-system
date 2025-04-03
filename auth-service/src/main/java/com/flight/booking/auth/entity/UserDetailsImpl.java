package com.flight.booking.auth.entity;

import com.flight.booking.auth.dto.UserResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UserDetailsImpl implements UserDetails {
    private final int id;
    private final String name;
    private final String email;
    private final String password;
    private final List<GrantedAuthority> authorities;

    public UserDetailsImpl(UserResponse user) {
        email = user.getEmail();
        password = user.getPassword();
        authorities = List.of(new SimpleGrantedAuthority(user.getRole()));
        id = user.getId();
        name = user.getName();
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
