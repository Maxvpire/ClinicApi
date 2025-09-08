package com.maxvpire.rates.rate;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RateRepository extends JpaRepository<Rate, String> {
    Optional<Rate> findRateByAppointmentId(String id);
    List<Rate> findRatesByPatientId(String id);
    List<Rate> findRatesByDoctorId(String id);
    List<Rate> findRatesByRated(boolean rated);
}
