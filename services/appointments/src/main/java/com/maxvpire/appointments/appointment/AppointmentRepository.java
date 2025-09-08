package com.maxvpire.appointments.appointment;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends MongoRepository<Appointment, String> {
    List<Appointment> getAppointmentsByDate(LocalDate date);
    List<Appointment> getAppointmentsByPatientId(String id);
    List<Appointment> getAppointmentsByDoctorId(String id);
    List<Appointment> getAppointmentByStatus(AppointmentStatus status);
    Optional<Appointment> getAppointmentByDateAndStart(LocalDate date, LocalTime start);
}
