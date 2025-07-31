package com.maxvpire.doctors.schedule;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maxvpire.doctors.doctor.Doctor;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Enumerated(EnumType.STRING)
    @Column(updatable = false)
    private Weekdays weekday;

    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "start_time")
    private LocalTime startTime;

    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "end_time")
    private LocalTime endTime;

    @CreatedDate
    private LocalDateTime created_at;

    @LastModifiedDate
    private LocalDateTime last_modified_at;
}