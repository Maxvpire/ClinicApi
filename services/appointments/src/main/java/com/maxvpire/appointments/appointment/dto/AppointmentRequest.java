package com.maxvpire.appointments.appointment.dto;

import com.maxvpire.appointments.appointment.AppointmentStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentRequest(
        @NotNull
        String patientId,

        @NotNull
        String doctorId,

        @NotNull
        LocalDate date,

        @NotNull
        @DateTimeFormat(pattern = "HH:mm")
        LocalTime start,

        @NotNull
        String roomId,

        AppointmentStatus status,
        String notes
) {
}
