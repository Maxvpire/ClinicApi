package com.maxvpire.doctors.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, String> {
    List<Schedule> findSchedulesByWeekday(Weekdays weekday);
    List<Schedule> findSchedulesByStartTimeGreaterThanEqualAndEndTimeLessThanEqual(LocalTime start_time, LocalTime end_time);
    List<Schedule> findSchedulesByDoctorId(String doctorId);
    @Query("SELECT s FROM Schedule s WHERE s.weekday = :weekday AND s.startTime < :end AND s.endTime > :start")
    List<Schedule> findOverlappingSchedules(
            @Param("weekday") Weekdays weekday,
            @Param("start") LocalTime start,
            @Param("end") LocalTime end
    );


}
