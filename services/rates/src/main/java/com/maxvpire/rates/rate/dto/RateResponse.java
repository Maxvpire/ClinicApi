package com.maxvpire.rates.rate.dto;

import java.time.LocalDateTime;

public record RateResponse(
    String id,
    String appointmentId,
    String patientId,
    String doctorId,
    double rating,
    String comment,
    boolean rated,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) { }