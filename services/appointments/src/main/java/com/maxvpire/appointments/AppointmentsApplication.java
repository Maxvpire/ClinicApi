package com.maxvpire.appointments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class AppointmentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppointmentsApplication.class, args);
	}

}
