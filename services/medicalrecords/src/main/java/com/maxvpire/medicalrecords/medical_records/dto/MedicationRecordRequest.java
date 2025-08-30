package com.maxvpire.medicalrecords.medical_records.dto;

import jakarta.validation.constraints.NotNull;

public record MedicationRecordRequest(
        @NotNull(message = "Patient is mandatory!")
        String patientId,

        @NotNull(message = "Doctor is mandatory!")
        String doctorId,

        @NotNull(message = "Appointment is mandatory!")
        String appointmentId,

        @NotNull(message = "Diagnosis is mandatory!")
        String diagnosis,

        @NotNull(message = "Treatment is mandatory!")
        String treatment
) {
}
