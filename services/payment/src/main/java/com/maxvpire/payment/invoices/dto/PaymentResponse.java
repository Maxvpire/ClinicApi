package com.maxvpire.payment.invoices.dto;

import com.maxvpire.payment.payments.PaymentMethod;

import java.time.LocalDateTime;

public record PaymentResponse(
        String id,
        double amount,
        PaymentMethod method,
        LocalDateTime createdAt,
        LocalDateTime updatedAT
) {

}
