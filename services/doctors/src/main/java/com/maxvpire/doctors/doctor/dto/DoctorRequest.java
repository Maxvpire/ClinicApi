package com.maxvpire.doctors.doctor;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record DoctorRequest(
        @NotNull(message = "Firstname is required!")
        String firstname,
        @NotNull(message = "Lastname is required!")
        String lastname,
        @NotNull(message = "Specialization is required!")
        String specialization,
        @Email("Email ")
        String email,
        @NotNull
        String phone,
        @NotNull
        Gender gender,
        @NotNull
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate dateOfBirth
) {
}
