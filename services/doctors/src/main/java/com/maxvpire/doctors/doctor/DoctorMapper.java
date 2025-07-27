package com.maxvpire.doctors.doctor;

import com.maxvpire.doctors.doctor.dto.DoctorResponse;
import org.springframework.stereotype.Service;

@Service
public class DoctorMapper {
    public DoctorResponse toDoctorResponse(Doctor doctor) {
        return new DoctorResponse(
                doctor.getId(),
                doctor.getFirstname(),
                doctor.getLastname(),
                doctor.getSpecialization(),
                doctor.getEmail(),
                doctor.getPhone(),
                doctor.getGender(),
                doctor.isActive(),
                doctor.getDateOfBirth(),
                doctor.getCreatedAt(),
                doctor.getUpdatedAt()
        );
    }
}
