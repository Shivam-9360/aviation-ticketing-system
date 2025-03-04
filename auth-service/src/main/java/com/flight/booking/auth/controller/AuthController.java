package com.flight.booking.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    @RequestMapping("/auth")
    public String Hello(){
        return "Hello from security";
    }
}
