package com.pp.userservice.controller;

import com.pp.userservice.model.User;
import com.pp.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public Mono<User> register(@Validated @RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping("/{email}")
    public Mono<User> getUserDetails(@PathVariable String email) {
        return userService.getUserDetails(email);
    }
}
