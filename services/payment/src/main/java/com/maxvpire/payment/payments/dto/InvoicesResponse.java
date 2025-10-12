package com.maxvpire.payment.payments.dto;


import com.maxvpire.payment.invoices.Status;

import java.time.LocalDateTime;

public record InvoicesResponse(
    String id,
    String patientId,
    String appointmentId,
    double amount,
    Status status,
    LocalDateTime createdDate,
    LocalDateTime updatedAt
) {

}
