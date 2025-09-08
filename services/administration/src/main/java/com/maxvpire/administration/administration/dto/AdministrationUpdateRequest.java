package com.maxvpire.administration.administration.dto;

import java.time.LocalDate;

public record AdministrationUpdateRequest(
        String firstname,
        String lastname,
        String phone,
        LocalDate dateOfBirth
) {
}
