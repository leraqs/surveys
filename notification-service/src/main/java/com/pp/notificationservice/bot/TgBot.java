package com.pp.notificationservice.bot;

import com.pp.notificationservice.config.BotConfig;
import com.pp.notificationservice.service.SubscriptionService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import reactor.core.publisher.Mono;

import static java.lang.String.format;

@Component
@Slf4j
@RequiredArgsConstructor
public class TgBot extends TelegramLongPollingBot {

    private final BotConfig config;
    private final SubscriptionService subscriptionService;

    private final String SUBSCRIBE_COMMAND = "/subscribe";
    private final String START_COMMAND = "/start";

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(@NotNull Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            String username = update.getMessage().getFrom().getFirstName();

            if (messageText.equals(START_COMMAND)) {
                startBot(chatId, username);
            } else if (messageText.startsWith(SUBSCRIBE_COMMAND)) {
                String userToSubscribe = messageText.substring(SUBSCRIBE_COMMAND.length()).trim();
                subscribeUser(chatId, userToSubscribe);
            } else {
                log.warn("Unexpected message");
            }
        }
    }

    private void subscribeUser(long chatId, String username) {
        subscriptionService.subscribe(chatId, username)
                .then(sendSubscriptionMessage(chatId, username))
                .subscribe();
    }

    private Mono<Void> sendSubscriptionMessage(long chatId, String username) {

        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(
                format("You have successfully subscribed to %s's new surveys!",
                        username));

        return Mono.fromCallable(() -> {
            try {
                execute(message);
                return Mono.empty();
            } catch (Exception e) {
                return Mono.error(e);
            }
        }).then();
    }


    private void startBot(long chatId, String username) {

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Hello, " + username);

        try {
            execute(message);
            log.info("Reply sent");
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

}

