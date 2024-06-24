package com.pp.notificationservice.service;

import com.pp.notificationservice.model.SurveyMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationService {

    private final TelegramService telegramService;
    private final SubscriptionService subscriptionService;

    @RabbitListener(queues = "${spring.rabbitmq.queue.name}")
    public void receiveMessage(SurveyMessage surveyMessage) {

        String creatorUsername = surveyMessage.creatorUsername();
        String text = format("New survey %s created by user %s",
                surveyMessage.title(), creatorUsername);

        subscriptionService.getSubscribersChatIds(creatorUsername)
                .flatMap(chatId -> telegramService.sendTelegramMessage(text, chatId))
                .subscribe();
    }

}
