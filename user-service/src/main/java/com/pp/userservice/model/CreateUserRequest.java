package com.pp.userservice.model;

public record CreateUserRequest(
        String username,
        String email,
        String password
) {
}

