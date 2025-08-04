package com.maxvpire.appointments.appointment;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document(collection = "appointment")
public class Appointment {
    @Id
    private String id;

    private String patientId;
    private String doctorId;
    private LocalDate date;
    private LocalTime start;
    private AppointmentStatus status = AppointmentStatus.SCHEDULED;
    private String notes;

    private String roomId;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
