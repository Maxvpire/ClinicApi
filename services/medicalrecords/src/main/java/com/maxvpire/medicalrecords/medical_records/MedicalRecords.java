package com.maxvpire.medicalrecords.medical_records;

import com.maxvpire.medicalrecords.prescriptions.Prescriptions;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "medical_records")
@EntityListeners(AuditingEntityListener.class)
public class MedicalRecords {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "patient_id", updatable = false)
    private String patientId;
    @Column(name = "doctor_id", updatable = false)
    private String doctorId;
    @Column(name = "appointment_id", unique = true, updatable = false)
    private String appointmentId;
    private String diagnosis;
    private String treatment;

    @OneToMany(mappedBy = "medicalRecords", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prescriptions> prescriptions = new ArrayList<>();

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
