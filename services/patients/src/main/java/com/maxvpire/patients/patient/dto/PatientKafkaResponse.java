package com.maxvpire.patients.patient.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientKafkaResponse {
    private String id;
    private String phone;
}
