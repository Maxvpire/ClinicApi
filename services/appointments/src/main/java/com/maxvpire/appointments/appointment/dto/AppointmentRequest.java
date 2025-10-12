package com.maxvpire.appointments.appointment.dto;

import com.maxvpire.appointments.appointment.AppointmentStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentRequest(
        @NotNull(message = "Patient is mandatory!")
        String patientId,

        @NotNull(message = "Doctor is mandatory!")
        String doctorId,

        @NotNull(message = "Date is mandatory!")
        @FutureOrPresent(message = "Appointment date and time cannot be in the past")
        LocalDate date,

        @NotNull(message = "Give starting time of the appointment!")
        @DateTimeFormat(pattern = "HH:mm")
        LocalTime start,

        @NotNull(message = "Room is mandatory!")
        String roomId,

        String notes
) {
}
