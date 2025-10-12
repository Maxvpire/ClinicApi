package com.maxvpire.payment.payments;

import com.maxvpire.payment.exceptions.PaymentAlreadyPaidException;
import com.maxvpire.payment.exceptions.PaymentNotFoundException;
import com.maxvpire.payment.invoices.Invoices;
import com.maxvpire.payment.invoices.InvoicesRepository;
import com.maxvpire.payment.invoices.Status;
import com.maxvpire.payment.payments.dto.PaymentResponse;
import com.maxvpire.payment.receipts.ReceiptRequest;
import com.maxvpire.payment.receipts.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final InvoicesRepository invoicesRepository;
    private final ReceiptService receiptService;
    private final PaymentMapper paymentMapper;

    public String createPayment(PaymentRequest request) throws Exception {
        Invoices invoices = invoicesRepository.findById(request.getInvoiceId())
                .orElseThrow(() -> new PaymentNotFoundException("Invoice not found"));
        if(invoices.getStatus().equals(Status.PAID)){
            throw new PaymentAlreadyPaidException("Payment already paid");
        }

        Payment payment = Payment.builder()
                .invoices(invoices)
                .amount(request.getAmount())
                .method(request.getMethod())
                .build();
        String id = paymentRepository.save(payment).getId();

        invoices.setStatus(Status.PAID);
        invoicesRepository.save(invoices);

        ReceiptRequest newReceipt = ReceiptRequest.builder()
                .paymentId(id)
                .invoiceId(request.getInvoiceId())
                .amount(request.getAmount())
                .method(request.getMethod())
                .build();

        return receiptService.generatePdfReceipt(newReceipt);
    }


    public List<PaymentResponse> findAll() {
        return paymentRepository.findAll()
                .stream()
                .map(paymentMapper::toPaymentDto)
                .collect(Collectors.toList());
    }

    public PaymentResponse findById(String id) {
        return paymentRepository.findById(id)
                .map(paymentMapper::toPaymentDto)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found"));
    }

    public List<PaymentResponse> findByMethod(PaymentMethod method) {
        if(method.equals(PaymentMethod.CARD)){
            return paymentRepository.findPaymentByMethod(PaymentMethod.CARD)
                    .stream()
                    .map(paymentMapper::toPaymentDto)
                    .collect(Collectors.toList());
        }
        if(method.equals(PaymentMethod.CASH)) {
            return paymentRepository.findPaymentByMethod(PaymentMethod.CASH)
                    .stream()
                    .map(paymentMapper::toPaymentDto)
                    .collect(Collectors.toList());
        }

        else {
            throw new UnsupportedOperationException("Method not supported");
        }
    }


    public void cancelPayment(String id) {
        paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found"));
        paymentRepository.deleteById(id);
    }
}
