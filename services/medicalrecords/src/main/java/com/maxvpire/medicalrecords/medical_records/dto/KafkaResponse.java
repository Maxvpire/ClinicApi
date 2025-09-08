package com.maxvpire.medicalrecords.medical_records.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KafkaResponse {
    private String id;
    private String patientId;
    private String doctorId;
}
