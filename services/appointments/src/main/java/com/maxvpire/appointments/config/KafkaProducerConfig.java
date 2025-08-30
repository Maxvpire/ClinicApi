package com.maxvpire.appointments.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaProducerConfig {
    @Bean
    public NewTopic getNewTopic() {
        return TopicBuilder.name("appointment-topic")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
