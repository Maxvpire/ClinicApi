package com.maxvpire.payment.invoices;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxvpire.payment.exceptions.InvoicesNotFoundException;
import com.maxvpire.payment.invoices.dto.InvoicesRequest;
import com.maxvpire.payment.invoices.dto.InvoicesResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InvoicesService {
    private final InvoicesRepository invoicesRepository;
    private final InvoicesMapper invoicesMapper;
    private final ObjectMapper objectMapper = new ObjectMapper();


    @KafkaListener(topics = "payments", groupId = "payment-consumer-group")
    public void consume(String message) {
        try{
            JsonNode root = objectMapper.readTree(message);
            String eventType = root.get("eventType").asText();
            JsonNode payload = root.get("payload");

            switch (eventType) {
                case "PAYMENT_CREATED":
                    InvoicesRequest request = InvoicesRequest.builder()
                        .patientId(payload.get("patientId").asText())
                        .appointmentId(payload.get("appointmentId").asText())
                        .amount(payload.get("amount").asDouble())
                        .build();

                    createInvoices(request);
                    log.info("Invoices created!");
                    break;

                case "PAYMENT_DELETED":
                    String appointmentId = payload.get("appointmentId").asText();
                    deleteInvoices(appointmentId);
                    log.info("Invoices deleted!");
                    break;

                default:
                    log.warn("Unknown event type from doctor event: {}", eventType);

            }
        }
        catch (Exception e){
            log.error("Failed to process message: {}", message, e);
        }
    }


    private void createInvoices(InvoicesRequest request) {
        Invoices invoices = Invoices.builder()
                .patientId(request.patientId())
                .appointmentId(request.appointmentId())
                .amount(request.amount())
                .status(Status.UNPAID)
                .build();

        invoicesRepository.save(invoices);
    }

    private void deleteInvoices(String appointmentId) {
        Invoices invoices = invoicesRepository.findByAppointmentId(appointmentId)
                .orElseThrow(() -> new InvoicesNotFoundException("Invoices with appointment id " + appointmentId + " not found!"));

        invoicesRepository.delete(invoices);
    }


    public List<InvoicesResponse> getAllInvoices() {
        return invoicesRepository.findAll()
                .stream()
                .map(invoicesMapper::toInvoicesDto)
                .collect(Collectors.toList());
    }

    public List<InvoicesResponse> getAllInvoicesByPatientId(String patientId) {
        List<InvoicesResponse> invoices = invoicesRepository.findByPatientId(patientId)
                .stream()
                .map(invoicesMapper::toInvoicesDto)
                .toList();
        if(invoices.isEmpty()) {
            throw new InvoicesNotFoundException("Invoices with patient id " + patientId + " not found!");
        }

        return invoices;
    }

    public InvoicesResponse getInvoicesByAppointmentId(String appointmentId) {
        return invoicesRepository.findByAppointmentId(appointmentId)
                .map(invoicesMapper::toInvoicesDto)
                .orElseThrow(() -> new InvoicesNotFoundException("Invoices with appointment id " + appointmentId + " not found!"));
    }

    public InvoicesResponse findById(String id) {
        return invoicesRepository.findById(id)
                .map(invoicesMapper::toInvoicesDto)
                .orElseThrow(() -> new InvoicesNotFoundException("Invoices with id " + id + " not found!"));
    }
}
