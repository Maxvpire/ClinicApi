package com.maxvpire.patients.patient;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends MongoRepository<Patient, String> {
    @Query("{ 'firstName': { $regex: ?0, $options: 'i' }, 'lastName': { $regex: ?0, $options: 'i' } }")
    List<Patient> searchPatients(String name);

    Optional<Patient> findPatientByPhone_number(String phoneNumber);
}
