package com.maxvpire.doctors.schedule;

import com.maxvpire.doctors.schedule.dto.ScheduleRequest;
import com.maxvpire.doctors.schedule.dto.ScheduleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/doctor/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping()
    public ResponseEntity<String> addSchedule(@RequestBody ScheduleRequest request) {
        return ResponseEntity.ok(scheduleService.addSchedule(request));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ScheduleResponse>> getAllSchedules() {
        return ResponseEntity.ok(scheduleService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ScheduleResponse> getScheduleById(@PathVariable String id) {
        return ResponseEntity.ok(scheduleService.findById(id));
    }

    @GetMapping("/weekday/{weekday}")
    public ResponseEntity<List<ScheduleResponse>> getScheduleByWeekday(@PathVariable Weekdays weekday) {
        return ResponseEntity.ok(scheduleService.findByWeekdays(weekday));
    }

    @GetMapping("/during")
    public ResponseEntity<List<ScheduleResponse>> getScheduleByFromAndTo(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime end) {
        return ResponseEntity.ok(scheduleService.findByWorkingTime(start, end));
    }

    @GetMapping("/when")
    public ResponseEntity<List<ScheduleResponse>> getScheduleByDuringWeekdayAndFromAndTo(
            @RequestParam("weekday") Weekdays weekday,
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime end) {
        return ResponseEntity.ok(scheduleService.findByWorkingTimeBetween(weekday, start, end));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteScheduleById(@PathVariable String id) {
        this.scheduleService.deleteById(id);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/doctor/{id}")
    public ResponseEntity<String> deleteScheduleByDoctorId(@PathVariable String id) {
        this.scheduleService.deleteByDoctorId(id);
        return ResponseEntity.accepted().build();
    }
}
