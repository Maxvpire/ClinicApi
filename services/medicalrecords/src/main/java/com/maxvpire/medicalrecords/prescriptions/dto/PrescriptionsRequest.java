package com.maxvpire.medicalrecords.prescriptions.dto;

import jakarta.validation.constraints.NotNull;

public record PrescriptionsRequest(
        @NotNull(message = "Medication record is mandatory!")
        String medical_records_id,

        @NotNull(message = "Medication name is mandatory!")
        String medicationName,

        @NotNull(message = "You have to give dosage too!")
        String dosage,

        @NotNull(message = "You have to give frequency too!")
        String frequency,

        @NotNull(message = "You have to give duration as well!")
        String duration
        ) {
}