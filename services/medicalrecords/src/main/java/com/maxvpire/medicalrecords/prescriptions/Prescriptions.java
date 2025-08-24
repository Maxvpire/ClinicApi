package com.maxvpire.medicalrecords.prescriptions;

import com.maxvpire.medicalrecords.medical_records.MedicalRecords;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "prescriptions")
@EntityListeners(AuditingEntityListener.class)
public class Prescriptions {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "medical_record_id", nullable = false)
    private MedicalRecords medicalRecords;

    @Column(name = "medication_name")
    private String medicationName;

    private String dosage;
    private String frequency;
    private String duration;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
