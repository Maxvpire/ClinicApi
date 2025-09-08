package com.maxvpire.appointments.appointment;

import com.maxvpire.appointments.appointment.dto.AppointmentRequest;
import com.maxvpire.appointments.appointment.dto.RatesKafkaResponse;
import com.maxvpire.appointments.exception.AppointmentNotFoundException;
import com.maxvpire.appointments.exception.PastTimeException;
import com.maxvpire.appointments.exception.RepeatedActionException;
import com.maxvpire.appointments.exception.RoomNotFoundException;
import com.maxvpire.appointments.room.Room;
import com.maxvpire.appointments.room.RoomRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final RoomRepository roomRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, RatesKafkaResponse> ratesKafkaTemplate;


    public String create(AppointmentRequest request) {
        Room room = roomRepository.findById(request.roomId())
                .orElseThrow(() -> new RoomNotFoundException("Room not found!"));
        Appointment appointment = Appointment.builder()
                .doctorId(request.doctorId())
                .patientId(request.patientId())
                .date(request.date())
                .start(request.start())
                .status(request.status())
                .roomId(room.getId())
                .notes(request.notes())
                .build();

        if (!isDateValid(request.date(), request.start())) {
            throw new PastTimeException("You can't put appointment to the past time!");
        }
        else{
            return appointmentRepository.save(appointment).getId();
        }
    }

    private boolean isDateValid(LocalDate date, LocalTime time) {
        LocalDate today = LocalDate.now();

        if(date.isAfter(today)) {
            return true;
        }
        else {
            if (date.isEqual(today)) {
                return !time.isBefore(LocalTime.now());
            }
            else{
                return false;
            }
        }
    }

    public List<Appointment> findAll() {
       return appointmentRepository.findAll();
    }

    public Appointment findById(String id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment with id: " + id + " not found!"));
    }


    public List<Appointment> getTodayAppointments() {
        return appointmentRepository.getAppointmentsByDate(LocalDate.now());
    }

    public List<Appointment> findByDate(LocalDate date) {
        return appointmentRepository.getAppointmentsByDate(date);
    }

    public List<Appointment> getByPatientId(String id) {
        return appointmentRepository.getAppointmentsByPatientId(id);
    }


    public List<Appointment> getByDoctorId(String id) {
        return appointmentRepository.getAppointmentsByDoctorId(id);
    }

    public List<Appointment> getByStatus(AppointmentStatus status) {
        return appointmentRepository.getAppointmentByStatus(status);
    }


    public Appointment findAppointmentByDateAndTime(LocalDate date, LocalTime start) {
        return appointmentRepository.getAppointmentByDateAndStart(date, start)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment with id: " + date + " and start: " + start + " not found!"));
    }

    public void completeAppointment(String id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment with id: " + id + " not found!"));
        appointment.setStatus(AppointmentStatus.COMPLETED);
        RatesKafkaResponse response = RatesKafkaResponse.builder()
                .id(appointment.getId())
                .patientId(appointment.getPatientId())
                .doctorId(appointment.getDoctorId())
                .build();

        if(appointment.getStatus() == AppointmentStatus.COMPLETED) {
            throw new RepeatedActionException("You can't complete completed appointment!");
        }

        else if(LocalDate.now().isEqual(appointment.getDate())) {
            if(LocalTime.now().isAfter(appointment.getStart())) {
                ratesKafkaTemplate.send("appointments", response);
                appointmentRepository.save(appointment);
            }
            else {
                throw new PastTimeException("You can't complete the appointment before it starts!");
            }
        }

        else if (appointment.getStatus().equals(AppointmentStatus.CANCELLED)) {
            throw new ValidationException("You can't complete canceled appointment!");
        }

        else if(LocalDate.now().isAfter(appointment.getDate())) {
            ratesKafkaTemplate.send("appointments", response);
            appointmentRepository.save(appointment);
        }

        else {
            throw  new PastTimeException("You can't complete the appointment before it starts!");
        }
    }

    public void cancelAppointment(String id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment with id: " + id + " not found!"));
        appointment.setStatus(AppointmentStatus.CANCELLED);

        if(appointment.getStatus().equals(AppointmentStatus.COMPLETED)) {
            throw new ValidationException("You can't cancel completed appointment!");
        }
        else if (appointment.getStatus().equals(AppointmentStatus.CANCELLED)) {
            throw new RepeatedActionException("You can't cancel canceled appointment!");
        }
        else if (LocalDate.now().isAfter(appointment.getDate())) {
            throw new ValidationException("You can't cancel pasted appointment!");
        }
        else {
            appointmentRepository.save(appointment);
        }
    }
}
