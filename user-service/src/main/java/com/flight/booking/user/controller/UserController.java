package com.flight.booking.user.controller;

import com.flight.booking.user.entity.User;
import com.flight.booking.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = service.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id){
        User user =  service.getUserById(id);
        return  ResponseEntity.ok(user);
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = service.createUser(user);
        return ResponseEntity.status(201).body(createdUser);
    }

    @PutMapping("/user")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User updateUser = service.updateUser(user);
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
