package com.maxvpire.doctors.schedule.dto;

import com.maxvpire.doctors.doctor.Gender;

import java.time.LocalDate;

public record ScheduleDoctor(
        String id,
        String firstname,
        String lastname,
        String specialization,
        String email,
        String phone,
        Gender gender,
        boolean isActive,
        boolean deleted,
        LocalDate dateofbirth
) {
}
