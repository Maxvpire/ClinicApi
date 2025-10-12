package com.maxvpire.payment.invoices;

import com.maxvpire.payment.invoices.dto.InvoicesResponse;
import com.maxvpire.payment.invoices.dto.PaymentResponse;
import com.maxvpire.payment.payments.Payment;
import org.springframework.stereotype.Service;

@Service
public class InvoicesMapper {
    public PaymentResponse toPaymentDto(Payment payment) {
        return new PaymentResponse(
                payment.getId(),
                payment.getAmount(),
                payment.getMethod(),
                payment.getCreatedAt(),
                payment.getUpdatedAt()
        );
    }
    public InvoicesResponse toInvoicesDto(Invoices invoices) {
        return new InvoicesResponse(
                invoices.getId(),
                invoices.getPatientId(),
                invoices.getAppointmentId(),
                invoices.getAmount(),
                invoices.getStatus(),
                toPaymentDto(invoices.getPayment()),
                invoices.getCreatedDate()
        );
    }
}
