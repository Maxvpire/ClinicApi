package com.maxvpire.medicalrecords.prescriptions.dto;

import jakarta.validation.constraints.NotNull;

public record PrescriptionsRequest(
        @NotNull
        String medical_records_id,

        @NotNull
        String medicationName,

        @NotNull
        String dosage,

        @NotNull
        String frequency,

        @NotNull
        String duration
        ) {
}