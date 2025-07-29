package com.maxvpire.patients.patient;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Patient {
    @Id
    private String id;

    private String firstname;
    private String lastname;
    private Gender gender;
    private LocalDate birth_date;

    @Indexed(unique = true)
    private String phone;

    @Indexed(unique = true)
    private String email;

    private Address address;

    private boolean banned = false;
    private boolean deleted = false;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
