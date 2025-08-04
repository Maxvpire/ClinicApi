package com.maxvpire.medical_records.medicalrecord;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document(collection = "medical_record")
public class MedicalRecord {
    @Id
    private String id;

    private String patientId;
    private String doctorId;
    private String appointmentId;
    private String diagnosis;

}
