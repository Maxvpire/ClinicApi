package com.maxvpire.medicalrecords.prescriptions.dto;


public record PrescriptionUpdateRequest(
        String medicationName,

        String dosage,

        String frequency,

        String duration
) {
}
