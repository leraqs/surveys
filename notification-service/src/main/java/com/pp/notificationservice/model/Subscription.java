package com.pp.notificationservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(name = "subscriptions")
public class Subscription {
    @Id
    private Long id;
    private Long chatId;
    private String username;
}
