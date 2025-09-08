package com.maxvpire.doctors.schedule.dto;

import com.maxvpire.doctors.schedule.Weekdays;

import java.time.LocalTime;

public record ScheduleResponse(
        String id,
        Weekdays weekday,
        LocalTime start_time,
        LocalTime end_time,
        ScheduleDoctorResponse doctor
) {
}
