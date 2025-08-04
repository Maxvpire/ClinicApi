package com.maxvpire.medical_records;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class MedicalRecordsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalRecordsApplication.class, args);
	}

}
