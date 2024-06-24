package com.pp.notificationservice.service;

import com.pp.notificationservice.model.SurveyMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final TelegramService telegramService;

    @RabbitListener(queues = "${spring.rabbitmq.queue.name}")
    public void receiveMessage(SurveyMessage surveyMessage, String chatId) {
        String text = "New survey created by user: " + surveyMessage.creatorUsername();
        telegramService.sendTelegramMessage(text, chatId);
    }
}
