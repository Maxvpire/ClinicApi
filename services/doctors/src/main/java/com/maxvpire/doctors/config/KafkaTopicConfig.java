package com.maxvpire.doctors.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic doctorTopic() {
        return TopicBuilder.name("doctors").build();
    }

    @Bean
    public NewTopic doctorDeletedTopic() {
        return TopicBuilder.name("delete-doctors").build();
    }
}
