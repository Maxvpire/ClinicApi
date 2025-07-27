package com.maxvpire.doctors.doctor.dto;

import com.maxvpire.doctors.doctor.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record UpdateDoctorRequest(
        @NotNull(message = "Firstname is required!")
        String firstname,
        @NotNull(message = "Lastname is required!")
        String lastname,
        @NotNull(message = "Phone number is required!")
        String phone
) {
}
