package com.musicbox.playlist.eventbus;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("user-exchange");
    }

    private static class ConsumerConfig {

        @Bean
        public Queue deleteUserQueue() {
            return new AnonymousQueue();
        }

        @Bean
        public Binding bindingUser(DirectExchange directExchangeUser, Queue deleteUserQueue) {
            return BindingBuilder.bind(deleteUserQueue).to(directExchangeUser).with("delete-user");
        }

        @Bean
        public MessageConsumer consumer() {
            return new MessageConsumer();
        }
    }
}
