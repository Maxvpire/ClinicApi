package com.maxvpire.medicalrecords.prescriptions.dto;

import java.time.LocalDateTime;

public record PrescriptionResponse(
    String id,
    String medicationName,
    String dosage,
    String frequency,
    String duration,
    LocalDateTime createdAt
) { }
