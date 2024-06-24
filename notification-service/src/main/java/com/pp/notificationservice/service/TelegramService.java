package com.pp.notificationservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TelegramService {

    @Value("${bot.token}")
    private String botToken;

    public String sendTelegramMessage(String message, String chatId) {
        String url = "https://api.telegram.org/bot" + botToken +
                "/sendMessage?chat_id=" + chatId +
                "&text=" + message;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }
}
