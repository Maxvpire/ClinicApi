package com.maxvpire.doctors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DoctorsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoctorsApplication.class, args);
	}

}
