package com.maxvpire.doctors.schedule;

import com.maxvpire.doctors.schedule.dto.ScheduleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/doctor/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping()
    public ResponseEntity<String> addSchedule(@RequestBody ScheduleRequest request) {
        return ResponseEntity.ok(scheduleService.addSchedule(request));
    }
}
