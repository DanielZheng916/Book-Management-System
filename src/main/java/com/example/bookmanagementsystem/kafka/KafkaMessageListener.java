package com.example.bookmanagementsystem.kafka;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaMessageListener {

    @Bean
    public MessageListener messageListener() {
        return new MessageListener();
    }

    public static class MessageListener{
        @KafkaListener(topics = "${author.topic.name}", groupId = "foo", containerFactory = "fooKafkaListenerContainerFactory")
        public void listenGroupFoo(String message) {
            System.out.println("Received Message in group 'foo': " + message);
        }
    }
}
