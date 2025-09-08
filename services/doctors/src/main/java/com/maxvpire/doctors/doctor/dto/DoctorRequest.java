package com.maxvpire.doctors.doctor.dto;

import com.maxvpire.doctors.doctor.Gender;
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
        @Email(message = "Invalid email format!")
        String email,
        @NotNull(message = "Phone number is required!")
        String phone,
        @NotNull(message = "Gender is required; And have to be MALE or Female!")
        Gender gender,
        @NotNull(message = "Date of birthday is required!")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate dateofbirth
) { }
