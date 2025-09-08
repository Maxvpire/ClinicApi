package com.maxvpire.administration.administration.dto;

import com.maxvpire.administration.administration.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AdministrationResponse(
        String id,
        String firstname,
        String lastname,
        String phone,
        String avatar,
        Gender gender,
        boolean isActive,
        boolean deleted,
        LocalDate dateOfBirth,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
