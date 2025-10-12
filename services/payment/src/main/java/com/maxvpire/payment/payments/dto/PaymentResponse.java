package com.maxvpire.payment.payments.dto;

import com.maxvpire.payment.payments.PaymentMethod;

import java.time.LocalDateTime;

public record PaymentResponse(
        String id,
        double amount,
        PaymentMethod method,
        InvoicesResponse invoice,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
