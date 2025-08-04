package com.maxvpire.appointments.appointment;

import com.maxvpire.appointments.appointment.dto.AppointmentRequest;
import com.maxvpire.appointments.exception.AppointmentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;


    public String create(AppointmentRequest request) {
        Appointment appointment = Appointment.builder()
                .doctorId(request.doctorId())
                .patientId(request.patientId())
                .date(request.date())
                .start(request.start())
                .status(request.status())
                .roomId(request.roomId())
                .notes(request.notes())
                .build();
        return appointmentRepository.save(appointment).getId();
    }

    public List<Appointment> findAll() {
       return appointmentRepository.findAll();
    }

    public Appointment findById(String id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment with id: " + id + " not found!"));
    }


    public List<Appointment> getTodayAppointments() {
        return appointmentRepository.getAppointmentsByDate(LocalDate.now());
    }

    public List<Appointment> findByDate(LocalDate date) {
        return appointmentRepository.getAppointmentsByDate(date);
    }

    public List<Appointment> getByPatientId(String id) {
        return appointmentRepository.getAppointmentsByPatientId(id);
    }


    public List<Appointment> getByDoctorId(String id) {
        return appointmentRepository.getAppointmentsByDoctorId(id);
    }

    public List<Appointment> getByStatus(AppointmentStatus status) {
        return appointmentRepository.getAppointmentByStatus(status);
    }


    public Appointment findAppointmentByDateAndTime(LocalDate date, LocalTime start) {
        return appointmentRepository.getAppointmentByDateAndStart(date, start)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment with id: " + date + " and start: " + start + " not found!"));
    }

    public void completeAppointment(String id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment with id: " + id + " not found!"));
        appointment.setStatus(AppointmentStatus.COMPLETED);
        appointmentRepository.save(appointment);
    }

    public void cancelAppointment(String id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment with id: " + id + " not found!"));
        appointment.setStatus(AppointmentStatus.CANCELLED);
        appointmentRepository.save(appointment);
    }
}
