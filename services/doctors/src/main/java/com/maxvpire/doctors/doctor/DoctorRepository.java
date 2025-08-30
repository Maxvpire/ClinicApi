package com.maxvpire.doctors.doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String> {
    @Query("SELECT u FROM Doctor u WHERE LOWER(u.firstname) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(u.lastname) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Doctor> searchByName(@Param("query") String query);
    @Query("SELECT u FROM Doctor u WHERE u.phone LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Doctor> searchByPhone(@Param("query") String query);
    List<Doctor> getDoctorByGender(Gender gender);
    Optional<Doctor> findDoctorByEmail(String email);
    Optional<Doctor> findDoctorByPhone(String phoneNumber);
}
