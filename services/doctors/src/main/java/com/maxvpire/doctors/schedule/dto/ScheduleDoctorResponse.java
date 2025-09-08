package com.maxvpire.doctors.schedule.dto;

import com.maxvpire.doctors.doctor.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ScheduleDoctorResponse(
        String id,
        String firstname,
        String lastname,
        String specialization,
        String email,
        String phone,
        String avatar,
        Gender gender,
        boolean isActive,
        boolean deleted,
        LocalDate dateofbirth,
        LocalDateTime created_at,
        LocalDateTime lastModifiedAt
) {
}
