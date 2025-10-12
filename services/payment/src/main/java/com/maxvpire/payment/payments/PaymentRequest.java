package com.maxvpire.payment.payments;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    @NotNull
    private String invoiceId;
    @NotNull
    private double amount;
    @NotNull
    private PaymentMethod method;
}
