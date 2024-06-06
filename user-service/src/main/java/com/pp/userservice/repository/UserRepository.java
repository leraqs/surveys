package com.pp.userservice.repository;

import com.pp.userservice.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {
    Mono<User> findByUsername(String username);

    Mono<User> findByEmail(String email);
}

