package com.maxvpire.administration.administration;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "administration")
@EntityListeners(AuditingEntityListener.class)
public class Administration {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    @Column(nullable = false, unique = true)
    private String phone;
    @Column(unique = true)
    private String avatar;
    @Column(updatable = false, nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "is_active", columnDefinition = "true")
    private boolean active;
    @Column(columnDefinition = "false")
    private boolean deleted;
    @Column(nullable = false, name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column(updatable = false, name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;
}