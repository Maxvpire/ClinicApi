package com.maxvpire.payment.invoices.dto;

import com.maxvpire.payment.invoices.Status;
import com.maxvpire.payment.payments.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoicesResponse {
    private String id;
    private String patientId;
    private String appointmentId;
    private Double amount;
    private Status status;
    private PaymentResponse payment;
    private LocalDateTime createdAt;
}
