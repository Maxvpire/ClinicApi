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
                .doctor(request.doctor())
                .start_time(request.start_time())
                .end_time(request.end_time())
                .weekday(request.weekday())
                .build();
        return scheduleRepository.save(schedule).getId();
    }
}
