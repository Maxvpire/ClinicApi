package com.maxvpire.appointments.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic appointmentTopic() {
        return TopicBuilder.name("appointments")
                .build();
    }

    @Bean
    public NewTopic paymentTopic() {
        return TopicBuilder.name("payments")
                .build();
    }
}
