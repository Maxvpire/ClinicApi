package com.maxvpire.medicalrecords.prescriptions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescriptions, String> {
    List<Prescriptions> findByMedicationNameContaining(String medicationName);
}
