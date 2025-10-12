package com.maxvpire.payment.invoices;

import com.maxvpire.payment.invoices.dto.InvoicesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/invoices")
@RequiredArgsConstructor
public class InvoicesController {
    private final InvoicesService invoicesService;

    @GetMapping("/all")
    public ResponseEntity<List<InvoicesResponse>> allInvoices() {
        return ResponseEntity.ok(invoicesService.getAllInvoices());
    }

    @GetMapping("id/{id}")
    public ResponseEntity<InvoicesResponse> getInvoice(@PathVariable("id") String id) {
        return ResponseEntity.ok(invoicesService.findById(id));
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<List<InvoicesResponse>> getInvoicesByPatientId(@PathVariable String id) {
        return ResponseEntity.ok(invoicesService.getAllInvoicesByPatientId(id));
    }

    @GetMapping("/appointment/{id}")
    public ResponseEntity<InvoicesResponse> getInvoicesByAppointmentId(@PathVariable String id) {
        return ResponseEntity.ok(invoicesService.getInvoicesByAppointmentId(id));
    }
}
