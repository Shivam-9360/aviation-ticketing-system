package com.flight.booking.user.controller;

import com.flight.booking.user.entity.User;
import com.flight.booking.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return  service.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable int id){
        return  service.getUserById(id);
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user){
        return  service.createUser(user);
    }

    @PutMapping("/user")
    public User updateUser(@RequestBody User user){
        return  service.updateUser(user);
    }

    @DeleteMapping("/user/{id}")
    private String deleteUserById(@PathVariable int id){
        service.deleteUserById(id);
        return "User Deleted";
    }

    @DeleteMapping("/users")
    private String deleteAllUsers(){
        service.deleteAllUsers();
        return "All Users Deleted";
    }
}
