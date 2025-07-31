package com.maxvpire.doctors.schedule;

import com.maxvpire.doctors.schedule.dto.ScheduleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public String addSchedule(ScheduleRequest request) {
        Schedule schedule = Schedule.builder()
                .doctors(request.doctor_id())
                .weekday(request.weekday())
                .start_time(request.start_time())
                .end_time(request.end_time())
                .build();
        System.out.println(request.weekday());
        return scheduleRepository.save(schedule).getId();
    }
}
