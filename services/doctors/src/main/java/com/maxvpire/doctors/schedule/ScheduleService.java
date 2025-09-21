package com.maxvpire.doctors.schedule;

import com.maxvpire.doctors.doctor.DoctorRepository;
import com.maxvpire.doctors.exception.BusyException;
import com.maxvpire.doctors.exception.ScheduleNotFoundException;
import com.maxvpire.doctors.schedule.dto.ScheduleRequest;
import com.maxvpire.doctors.schedule.dto.ScheduleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final DoctorRepository doctorRepository;
    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    public String addSchedule(ScheduleRequest request) {
        this.doctorRepository.findById(request.doctor().getId())
                .orElseThrow(() -> new ScheduleNotFoundException("Doctor not found!"));

        Schedule schedule = Schedule.builder()
                    .doctor(request.doctor())
                    .startTime(request.start_time())
                    .endTime(request.end_time())
                    .weekday(request.weekday())
                    .build();
        return scheduleRepository.save(schedule).getId();
    }

    public List<ScheduleResponse> findAll() {
        return scheduleRepository.findAll()
                .stream()
                .map(scheduleMapper::toScheduleResponse)
                .collect(Collectors.toList());
    }

    public ScheduleResponse findById(String id) {
        return scheduleRepository.findById(id)
                .map(scheduleMapper::toScheduleResponse)
                .orElseThrow(() -> new ScheduleNotFoundException("Schedule not found!"));
    }

    public List<ScheduleResponse> findByWeekdays(Weekdays weekday) {
        return scheduleRepository.findSchedulesByWeekday(weekday)
                .stream()
                .map(scheduleMapper::toScheduleResponse)
                .collect(Collectors.toList());
    }

    public List<ScheduleResponse> findByWorkingTime(LocalTime start, LocalTime end) {
        return scheduleRepository.findSchedulesByStartTimeGreaterThanEqualAndEndTimeLessThanEqual(
            start, end
        )
                .stream()
                .map(scheduleMapper::toScheduleResponse)
                .collect(Collectors.toList());
    }

    public List<ScheduleResponse> findByWorkingTimeBetween(Weekdays weekday, LocalTime start, LocalTime end) {
        return scheduleRepository.findOverlappingSchedules(weekday,start, end)
                .stream()
                .map(scheduleMapper::toScheduleResponse)
                .collect(Collectors.toList());
    }

    public void deleteById(String id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException("Schedule not found!"));
        scheduleRepository.delete(schedule);
    }

    public void deleteByDoctorId(String doctorId) {
        List<Schedule> schedule = scheduleRepository.findSchedulesByDoctorId(doctorId);
        scheduleRepository.deleteAll(schedule);
    }
}
