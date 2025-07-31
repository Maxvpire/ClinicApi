package com.maxvpire.doctors.doctor.dto;

import com.maxvpire.doctors.schedule.Weekdays;
import com.maxvpire.doctors.schedule.dto.ScheduleDoctorResponse;

import java.time.LocalTime;

public record DoctorSchedulesResponse(
        String id,
        Weekdays weekday,
        LocalTime start_time,
        LocalTime end_time
) {
}
