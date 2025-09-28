package com.maxvpire.payment.invoices;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoicesRepository extends JpaRepository<Invoices, String> {
}
