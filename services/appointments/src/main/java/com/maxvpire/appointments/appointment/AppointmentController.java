package com.maxvpire.appointments.appointment;

import com.maxvpire.appointments.appointment.dto.AppointmentRequest;
import com.maxvpire.appointments.appointment.dto.PaymentAmountRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/appointment")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<String> createAppointment(
            @RequestBody @Valid AppointmentRequest request
    ) {
        return ResponseEntity.ok(appointmentService.create(request));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable String id) {
        return ResponseEntity.ok(appointmentService.findById(id));
    }

    @GetMapping("/today")
    public ResponseEntity<List<Appointment>> getTodayAppointments() {
        return ResponseEntity.ok(appointmentService.getTodayAppointments());
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Appointment>> getAppointmentsByDate(@PathVariable LocalDate date) {
        return ResponseEntity.ok(appointmentService.findByDate(date));
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<List<Appointment>> getPatientAppointments(@PathVariable String id) {
        return ResponseEntity.ok(appointmentService.getByPatientId(id));
    }

    @GetMapping("/doctor/{id}")
    public ResponseEntity<List<Appointment>> getDoctorAppointments(@PathVariable String id) {
        return ResponseEntity.ok(appointmentService.getByDoctorId(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Appointment>> getAppointmentsByStatus(@PathVariable AppointmentStatus status) {
        return ResponseEntity.ok(appointmentService.getByStatus(status));
    }

    @GetMapping("/date/{date}/in/{start}")
    public ResponseEntity<Appointment> getAppointmentsByDateAndTime(
            @PathVariable LocalDate date,
            @PathVariable LocalTime start)
    {
        return ResponseEntity.ok(appointmentService.findAppointmentByDateAndTime(date, start));
    }

    @PostMapping("/complete/{id}")
    public ResponseEntity<String> completeAppointment(@PathVariable String id, @Valid @RequestBody PaymentAmountRequest request) {
        this.appointmentService.completeAppointment(id, request);
        return ResponseEntity.ok("Appointment has been completed");
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<String> cancelAppointment(@PathVariable String id) {
        this.appointmentService.cancelAppointment(id);
        return ResponseEntity.ok("Appointment has been cancelled");
    }
}
