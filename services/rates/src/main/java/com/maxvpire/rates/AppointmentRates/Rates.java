package com.maxvpire.rates.AppointmentRates;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table("rates")
public class Rates {
    @PrimaryKey
    private String id = UUID.randomUUID().toString();

    @Column("appointment_id")
    private String appointmentId;

    @Column("patient_id")
    private String patientId;

    @Column("doctor_id")
    private String doctorId;

    private int rating;

    private String comment;

    private boolean rated = false;

    @Column("created_at")
    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();
}
