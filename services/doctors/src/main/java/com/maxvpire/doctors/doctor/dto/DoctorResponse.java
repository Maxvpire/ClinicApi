package com.maxvpire.doctors.doctor.dto;

import com.maxvpire.doctors.doctor.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DoctorResponse(
    String id,
    String firstname,
    String lastname,
    String specialization,
    String email,
    String phone,
    Gender gender,
    boolean isActive,
    LocalDate dateOfBirth,
    LocalDateTime createdAt,
    LocalDateTime lastModifiedDate
) {
}
