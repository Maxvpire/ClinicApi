package com.maxvpire.patients.patient.dto;

import com.maxvpire.patients.patient.Address;
import jakarta.validation.constraints.NotNull;

public record UpdatePatientRequest(
        @NotNull(message = "Firstname is required!")
        String firstname,
        @NotNull(message = "Lastname is required!")
        String lastname,
        @NotNull(message = "Phone number is required!")
        String phone,
        Address address
) {
}
