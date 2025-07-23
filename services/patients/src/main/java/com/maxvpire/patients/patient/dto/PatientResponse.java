package com.maxvpire.patients.patient.dto;

import com.maxvpire.patients.patient.Address;
import com.maxvpire.patients.patient.Gender;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record PatientResponse(
        String id,
        String firstname,
        String lastname,
        Gender gender,
        LocalDate birth_date,
        String phone_number,
        String email,
        Address address,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
