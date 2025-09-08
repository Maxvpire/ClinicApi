package com.maxvpire.doctors.doctor;

import com.maxvpire.doctors.doctor.dto.DoctorResponse;
import com.maxvpire.doctors.doctor.dto.DoctorSchedulesResponse;
import com.maxvpire.doctors.schedule.Schedule;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorMapper {
    public DoctorSchedulesResponse toScheduleDto(Schedule schedule) {
        return new DoctorSchedulesResponse(
                schedule.getId(),
                schedule.getWeekday(),
                schedule.getStartTime(),
                schedule.getEndTime()
        );
    }

    public DoctorResponse toDoctorResponse(Doctor doctor) {
        List<DoctorSchedulesResponse> schedules = new ArrayList<>();
        for (Schedule schedule : doctor.getSchedules()) {
            DoctorSchedulesResponse scheduleDto = toScheduleDto(schedule);
            schedules.add(scheduleDto);
        }
        return new DoctorResponse(
                doctor.getId(),
                doctor.getFirstname(),
                doctor.getLastname(),
                doctor.getSpecialization(),
                doctor.getEmail(),
                doctor.getPhone(),
                doctor.getAvatar(),
                doctor.getGender(),
                doctor.is_active(),
                doctor.isDeleted(),
                doctor.getDateofbirth(),
                schedules,
                doctor.getCreated_at(),
                doctor.getLast_modified_at()
        );
    }
}
