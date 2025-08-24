package com.maxvpire.medicalrecords;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MedicalrecordsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalrecordsApplication.class, args);
	}

}
