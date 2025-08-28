package com.maxvpire.doctors.schedule;

import com.maxvpire.doctors.doctor.Doctor;
import com.maxvpire.doctors.schedule.dto.ScheduleDoctorResponse;
import com.maxvpire.doctors.schedule.dto.ScheduleResponse;
import org.springframework.stereotype.Service;

@Service
public class ScheduleMapper {
    public static ScheduleDoctorResponse toDoctorDTO(Doctor doctor) {
        return new ScheduleDoctorResponse(
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
                doctor.getCreated_at(),
                doctor.getLast_modified_at()
        );
    }

    public ScheduleResponse toScheduleResponse(Schedule schedule) {
        return new ScheduleResponse(
                schedule.getId(),
                schedule.getWeekday(),
                schedule.getStartTime(),
                schedule.getEndTime(),
                toDoctorDTO(schedule.getDoctor())
        );
    }
}
