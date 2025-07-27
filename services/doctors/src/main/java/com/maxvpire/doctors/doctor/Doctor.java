package com.maxvpire.doctors.doctor;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "doctor")
@EntityListeners(AuditingEntityListener.class)
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String firstname;
    private String lastname;
    @Column(updatable = false)
    private String specialization;
    @Column(unique = true, updatable = false)
    private String email;
    @Column(unique = true)
    private String phone;

    @Column(updatable = false)
    private Gender gender;

    @Column(columnDefinition = "true")
    private boolean isActive;

    @Column(updatable = false)
    private LocalDate dateOfBirth;

    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
