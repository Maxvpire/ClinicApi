package com.maxvpire.doctors.doctor;

import com.maxvpire.doctors.schedule.Schedule;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Column(unique = true)
    private String avatar;

    @Column(updatable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(columnDefinition = "true")
    private boolean is_active;

    @Column(columnDefinition = "false")
    private boolean deleted;

    @Column(updatable = false)
    private LocalDate dateofbirth;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Schedule> schedules = new ArrayList<>();

    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime created_at;

    @LastModifiedDate
    private LocalDateTime last_modified_at;
}
