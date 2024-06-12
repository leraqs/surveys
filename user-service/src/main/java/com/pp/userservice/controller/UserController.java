package com.pp.userservice.controller;

import com.pp.userservice.model.CreateUserRequest;
import com.pp.userservice.model.User;
import com.pp.userservice.model.UserDetails;
import com.pp.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public User register(@Validated @RequestBody CreateUserRequest user) {
        return userService.registerUser(user);
    }

    @GetMapping("/{uid}")
    public UserDetails getUserDetails(@PathVariable String uid) {
        return userService.getUserDetails(uid);
    }
}
