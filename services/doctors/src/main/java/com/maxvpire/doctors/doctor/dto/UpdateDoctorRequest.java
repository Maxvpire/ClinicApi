package com.maxvpire.doctors.doctor.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateDoctorRequest(
        @NotNull(message = "Firstname is required!")
        String firstname,
        @NotNull(message = "Lastname is required!")
        String lastname,
        @NotNull(message = "Phone number is required!")
        String phone
) {
}
