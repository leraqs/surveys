package com.pp.userservice.service;

import com.pp.userservice.model.User;
import com.pp.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Mono<User> registerUser(User user) {
        return userRepository.save(user);
    }

    public Mono<User> getUserDetails(String email) {
        return userRepository.findByEmail(email);
    }
}

