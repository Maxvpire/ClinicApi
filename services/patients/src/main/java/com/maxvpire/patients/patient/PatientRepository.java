package com.maxvpire.patients.patient;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends MongoRepository<Patient, String> {
    @Query("{ '$or': [ " +
            "{ 'firstname': { $regex: ?0, $options: 'i' } }, " +
            "{ 'lastname': { $regex: ?0, $options: 'i' } } " +
            "] }")
    List<Patient> searchByName(String query);

    @Query("{'phone':  {$regex: ?0, $options:  'i'}}")
    List<Patient> searchByPhone(String phone);

    Optional<Patient> findPatientByPhone(String phone);
}
