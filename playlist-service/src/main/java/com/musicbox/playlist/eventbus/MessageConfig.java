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
        public Queue newUserQueue() {
            return new AnonymousQueue();
        }

        @Bean
        public Queue deleteUserQueue() {
            return new AnonymousQueue();
        }

        @Bean
        public Binding bindingNewUser(DirectExchange directExchangeUser, Queue newUserQueue) {
            return BindingBuilder.bind(newUserQueue).to(directExchangeUser).with("new-user");
        }

        @Bean
        public Binding bindingDeleteUser(DirectExchange directExchangeUser, Queue deleteUserQueue) {
            return BindingBuilder.bind(deleteUserQueue).to(directExchangeUser).with("delete-user");
        }

        @Bean
        public MessageConsumer consumer() {
            return new MessageConsumer();
        }
    }
}
