package com.maxvpire.payment.receipts;

import com.maxvpire.payment.payments.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptRequest {
    private String invoiceId;
    private String paymentId;
    private double amount;
    private PaymentMethod method;
}
