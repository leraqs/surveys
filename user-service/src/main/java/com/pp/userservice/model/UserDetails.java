package com.pp.userservice.model;

public record UserDetails(
        String username,
        String email
) {

    public UserDetails(User user) {
        this(user.getUsername(), user.getEmail());
    }
}
