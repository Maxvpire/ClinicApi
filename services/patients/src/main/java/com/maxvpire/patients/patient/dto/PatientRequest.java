package com.maxvpire.patients.patient.dto;


import com.maxvpire.patients.patient.Address;
import com.maxvpire.patients.patient.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PatientRequest(
        @NotNull(message = "Patient's firstname is required!")
        String firstname,
        @NotNull(message = "Patient's lastname is required!")
        String lastname,
        Gender gender,
        @NotNull(message = "Patient's birthday day is required!")
        LocalDate birth_date,
        @NotNull(message = "Patient's phone number is required!")
        String phone,
        @Email(message = "Invalid email format!")
        String email,
        Address address
) {

}
