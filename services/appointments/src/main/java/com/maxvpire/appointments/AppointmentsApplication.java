package com.maxvpire.appointments;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
@EnableMongoAuditing
public class AppointmentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppointmentsApplication.class, args);
	}

}
