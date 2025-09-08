package com.maxvpire.administration.administration.dto;

import com.maxvpire.administration.administration.Gender;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AdministrationRequest(
    @NotNull(message = "Firstname is required!")
    String firstname,
    @NotNull(message = "Lastname is required!")
    String lastname,
    @NotNull(message = "Phone number is required!")
    String phone,
    @NotNull(message = "Gender is required; And have to be MALE or Female!")
    Gender gender,
    @NotNull(message = "Date of birthday is mandatory!")
    LocalDate dateOfBirth
) {
}
