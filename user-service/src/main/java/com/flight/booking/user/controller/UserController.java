package com.flight.booking.user.controller;

import com.flight.booking.user.dto.UserRequest;
import com.flight.booking.user.dto.UserResponse;
import com.flight.booking.user.entity.User;
import com.flight.booking.user.service.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = service.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable int id){
        UserResponse user =  service.getUserById(id);
        return  ResponseEntity.ok(user);
    }

    @PostMapping("/user")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest user) {
        UserResponse createdUser = service.createUser(user);
        return ResponseEntity.status(201).body(createdUser);
    }

    @PutMapping("/user")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest user){
        UserResponse updateUser = service.updateUser(user);
        return ResponseEntity.status(201).body(updateUser);
    }

    @DeleteMapping("/user/{id}")
    private ResponseEntity<String> deleteUserById(@PathVariable int id){
        service.deleteUserById(id);
        return ResponseEntity.ok("User Deleted");
    }

    @DeleteMapping("/users")
    private ResponseEntity<String> deleteAllUsers(){
        service.deleteAllUsers();
        return ResponseEntity.ok("All Users Deleted");
    }
}
