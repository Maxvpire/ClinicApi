package com.maxvpire.doctors.doctor.dto;

import com.maxvpire.doctors.doctor.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record DoctorResponse(
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
        List<DoctorSchedulesResponse> schedules,
        LocalDateTime created_at,
        LocalDateTime lastModifiedAt
) {
}
