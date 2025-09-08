package com.maxvpire.medicalrecords.medical_records;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MedicalRecordsRepository extends JpaRepository<MedicalRecords, String> {
    List<MedicalRecords> findByPatientId(String  patientId);
    List<MedicalRecords> findByDoctorId(String  doctorId);
    Optional<MedicalRecords> findByAppointmentId(String appointmentId);
    List<MedicalRecords> findByCreatedAtBetween(LocalDateTime from, LocalDateTime to);
}
