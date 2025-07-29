package com.maxvpire.doctors.schedule.dto;

import com.maxvpire.doctors.doctor.Doctor;
import com.maxvpire.doctors.schedule.Weekdays;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

public record ScheduleRequest(
        Doctor doctor,
        @NotNull
        Weekdays weekday,
        @NotNull
        @DateTimeFormat(pattern = "HH:mm")
        LocalTime start_time,
        @NotNull
        @DateTimeFormat(pattern = "HH:mm")
        LocalTime end_time
) {
}
