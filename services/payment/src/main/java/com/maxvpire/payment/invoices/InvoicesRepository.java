package com.maxvpire.payment.invoices;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InvoicesRepository extends JpaRepository<Invoices, String> {
    Optional<Invoices> findByAppointmentId(String s);
    List<Invoices> findByPatientId(String s);
}
