package com.maxvpire.medicalrecords.medical_records.dto;

import jakarta.validation.constraints.NotNull;

public record MedicationRecordRequest(
        @NotNull
        String patientId,

        @NotNull
        String doctorId,

        @NotNull
        String appointmentId,

        @NotNull
        String diagnosis,

        @NotNull
        String treatment
) {
}
