package com.pp.notificationservice.service;

import com.pp.notificationservice.model.Subscription;
import com.pp.notificationservice.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public Mono<Subscription> subscribe(Long chatId, String username) {
        return subscriptionRepository.findByChatIdAndUsername(chatId, username)
                .switchIfEmpty(Mono.defer(() -> {
                    Subscription subscription = new Subscription();
                    subscription.setChatId(chatId);
                    subscription.setUsername(username);
                    return subscriptionRepository.save(subscription);
                }));
    }

    public Mono<Boolean> isSubscribed(Long chatId, String username) {
        return subscriptionRepository.findByChatIdAndUsername(chatId, username)
                .map(subscription -> true)
                .defaultIfEmpty(false);
    }

    public Flux<String> getSubscribersChatIds(String username) {
        return subscriptionRepository.findChatIdsByUsername(username);
    }
}

