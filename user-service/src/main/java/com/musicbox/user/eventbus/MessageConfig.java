package com.musicbox.user.eventbus;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {


    @Bean("user-exchange")
    public DirectExchange directExchange() {
        return new DirectExchange("user-exchange");
    }

    @Bean
    public MessageProducer producer() {
        return new MessageProducer();
    }
}
