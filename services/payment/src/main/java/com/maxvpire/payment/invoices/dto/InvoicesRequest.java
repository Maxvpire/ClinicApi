package com.maxvpire.payment.invoices.dto;

import lombok.Builder;

@Builder
public record InvoicesRequest(
        String patientId,
        String appointmentId,
        double amount
) {
}
