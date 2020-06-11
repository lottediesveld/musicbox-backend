package com.musicbox.user.eventbus;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MessageProducer {

    @Autowired
    private RabbitTemplate template;

    @Qualifier("user-exchange")
    @Autowired
    private DirectExchange directExchange;

    public void newPlaylistForUser(String message) {
        template.convertAndSend(directExchange.getName(), "new-user", message);
    }

    public void deletePlaylistFromUser(String message) {
        template.convertAndSend(directExchange.getName(), "delete-user", message);
    }
}