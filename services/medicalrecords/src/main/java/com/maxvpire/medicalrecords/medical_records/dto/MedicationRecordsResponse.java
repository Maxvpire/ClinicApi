package com.maxvpire.medicalrecords.medical_records.dto;


import com.maxvpire.medicalrecords.prescriptions.Prescriptions;

import java.time.LocalDateTime;
import java.util.List;

public record MedicationRecordsResponse(
   String id,
   String patientId,
   String doctorId,
   String appointmentId,
   String diagnosis,
   String treatment,
   LocalDateTime createdAt
) {}