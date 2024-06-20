package com.pp.notificationservice.repository;

import com.pp.notificationservice.model.Subscription;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface SubscriptionRepository extends ReactiveCrudRepository<Subscription, Long> {
    Mono<Subscription> findByChatIdAndUsername(Long chatId, String username);
}
