package com.maxvpire.medicalrecords.medical_records.dto;

import com.maxvpire.medicalrecords.prescriptions.dto.PrescriptionResponse;

import java.time.LocalDateTime;
import java.util.List;

public record MedicationRecordsWithPrescriptionsResponse(
        String id,
        String patientId,
        String doctorId,
        String appointmentId,
        String diagnosis,
        String treatment,
        List<PrescriptionResponse> prescriptions,
        LocalDateTime createdAt
) {
}
