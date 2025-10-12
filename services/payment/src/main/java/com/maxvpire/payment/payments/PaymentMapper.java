package com.maxvpire.payment.payments;

import com.maxvpire.payment.invoices.Invoices;
import com.maxvpire.payment.payments.dto.InvoicesResponse;
import com.maxvpire.payment.payments.dto.PaymentResponse;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {
    public InvoicesResponse toInvoicesDto(Invoices invoices) {
        return new InvoicesResponse(
                invoices.getId(),
                invoices.getPatientId(),
                invoices.getAppointmentId(),
                invoices.getAmount(),
                invoices.getStatus(),
                invoices.getCreatedDate(),
                invoices.getUpdatedAt()
        );
    }

    public PaymentResponse toPaymentDto(Payment payment) {
        return new PaymentResponse(
                payment.getId(),
                payment.getAmount(),
                payment.getMethod(),
                toInvoicesDto(payment.getInvoices()),
                payment.getCreatedAt(),
                payment.getUpdatedAt()
        );
    }
}
