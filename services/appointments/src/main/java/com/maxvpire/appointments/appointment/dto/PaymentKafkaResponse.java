package com.maxvpire.appointments.appointment.dto;

import lombok.Builder;

@Builder
public record PaymentKafkaResponse(
        String id,
        String patientId,
        String doctorId,
        double amount
) {
}
