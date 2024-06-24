package com.pp.notificationservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class TelegramService {

    @Value("${bot.token}")
    private String botToken;

    public Mono<Void> sendTelegramMessage(String message, String chatId) {
        String url = "https://api.telegram.org/bot" + botToken +
                "/sendMessage?chat_id=" + chatId +
                "&text=" + URLEncoder.encode(message, StandardCharsets.UTF_8);

        return WebClient.create()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(Void.class);
    }

}
