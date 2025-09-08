package com.maxvpire.appointments.appointment.dto;

import lombok.Builder;

@Builder
public record RatesKafkaResponse(
        String id,
        String patientId,
        String doctorId
) {
}
