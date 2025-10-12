package com.maxvpire.payment.payments;

import com.maxvpire.payment.payments.dto.PaymentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<String> createPayment(@RequestBody @Valid PaymentRequest request) throws Exception {
        return ResponseEntity.ok(paymentService.createPayment(request));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PaymentResponse>> getAllPayments(@RequestParam Integer page, @RequestParam Integer size) {
        return ResponseEntity.ok(paymentService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable String id) {
        return ResponseEntity.ok(paymentService.findById(id));
    }

    @GetMapping("/method/{method}")
    public ResponseEntity<List<PaymentResponse>> getPaymentMethod(@PathVariable String method) {
        return ResponseEntity.ok(paymentService.findByMethod(PaymentMethod.valueOf(method.toUpperCase())));
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<?> cancelPayment(@PathVariable String id) {
        paymentService.cancelPayment(id);
        return ResponseEntity.accepted().build();
    }
}