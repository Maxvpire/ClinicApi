package com.maxvpire.patients.patient;

import com.maxvpire.patients.patient.dto.PatientRequest;
import com.maxvpire.patients.patient.dto.PatientResponse;
import org.springframework.stereotype.Service;

@Service
public class PatientMapper {
    public Patient toPatient(PatientRequest request) {
        if(request == null) {
            return null;
        }
        return Patient.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .gender(request.gender())
                .birth_date(request.birth_date())
                .email(request.email())
                .phone(request.phone())
                .address(request.address())
                .build();
    }

    public PatientResponse fromPatient(Patient patient) {
        return new PatientResponse(
                patient.getId(),
                patient.getFirstname(),
                patient.getLastname(),
                patient.getGender(),
                patient.getBirth_date(),
                patient.getPhone(),
                patient.getEmail(),
                patient.isBanned(),
                patient.isDeleted(),
                patient.getAddress(),
                patient.getCreatedAt(),
                patient.getUpdatedAt()
        );
    }
}
