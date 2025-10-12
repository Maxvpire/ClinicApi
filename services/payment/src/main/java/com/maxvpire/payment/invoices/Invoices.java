package com.maxvpire.payment.invoices;

import com.maxvpire.payment.payments.Payment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invoices")
@EntityListeners(AuditingEntityListener.class)
public class Invoices {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "patient_id", nullable = false)
    private String patientId;

    @Column(name = "appointment_id", unique = true, nullable = false)
    private String appointmentId;

    @Column(name = "amount")
    private Double amount;

    @OneToOne(mappedBy = "invoices", cascade = CascadeType.ALL, orphanRemoval = true)
    private Payment payment;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "updated_at",  nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
