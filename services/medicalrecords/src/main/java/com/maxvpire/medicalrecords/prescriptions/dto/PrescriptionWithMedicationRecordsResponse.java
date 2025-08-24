package com.maxvpire.medicalrecords.prescriptions.dto;

import com.maxvpire.medicalrecords.medical_records.dto.MedicationRecordsResponse;

import java.time.LocalDateTime;

public record PrescriptionWithMedicationRecordsResponse (
    String id,
    MedicationRecordsResponse medicalRecords,
    String medicationName,
    String dosage,
    String frequency,
    String duration,
    LocalDateTime createdAt
){}