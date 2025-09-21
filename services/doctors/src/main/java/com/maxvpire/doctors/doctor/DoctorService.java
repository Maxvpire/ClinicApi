package com.maxvpire.doctors.doctor;

import com.maxvpire.doctors.doctor.dto.DoctorRequest;
import com.maxvpire.doctors.doctor.dto.DoctorResponse;
import com.maxvpire.doctors.doctor.dto.DoctorTopicResponse;
import com.maxvpire.doctors.doctor.events.DoctorCreatedEvent;
import com.maxvpire.doctors.doctor.events.DoctorDeletedEvent;
import com.maxvpire.doctors.doctor.events.EventMessage;
import com.maxvpire.doctors.doctor.events.EventsService;
import com.maxvpire.doctors.exception.DoctorNotFoundException;
import com.maxvpire.doctors.exception.NotValidGenderTypeException;
import com.maxvpire.doctors.exception.RepeatedActionException;
import com.maxvpire.doctors.exception.UniqueException;
import com.maxvpire.doctors.schedule.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final ScheduleService scheduleService;
    private final EventsService eventsService;


    public String create(DoctorRequest request) {

        if(!request.gender().equals(Gender.MALE) && !request.gender().equals(Gender.FEMALE)){
            throw new NotValidGenderTypeException("This gender is not valid!");
        }

        Doctor doctor = Doctor.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .phone(request.phone())
                .dateofbirth(request.dateofbirth())
                .specialization(request.specialization())
                .gender(request.gender())
                .build();

        String doctorId = doctorRepository.save(doctor).getId();
        DoctorCreatedEvent response = DoctorCreatedEvent.builder()
                .id(doctorId)
                .phone(doctor.getPhone())
                .build();

        eventsService.sendEvent(response);
        return doctorId;
    }


    public List<DoctorResponse> findAll() {
        return doctorRepository.findAll()
                .stream()
                .map(doctorMapper::toDoctorResponse)
                .collect(Collectors.toList());

    }


    public DoctorResponse findById(String id) {
        return doctorRepository.findById(id)
                .map(doctorMapper::toDoctorResponse)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor with id: " + id + " not found"));
    }

    public void uploadAvatar(String id, String filename) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor with id: " + id + " not found"));

        doctor.setAvatar(filename);
        doctorRepository.save(doctor);
    }


    public void updateDoctor(String id, DoctorRequest request) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor with id: " + id + " not found"));
        mergeDoctor(doctor, request);
        doctorRepository.save(doctor);
    }

    private void mergeDoctor(Doctor doctor, DoctorRequest request) {
        if(StringUtils.isNotBlank(request.firstname())) {
            doctor.setFirstname(request.firstname());
        }
        if(StringUtils.isNotBlank(request.lastname())) {
            doctor.setLastname(request.lastname());
        }

        if(StringUtils.isNotBlank(request.phone())) {
            doctor.setPhone(request.phone());
        }

    }

    public void inActiveDoctor(String id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor with id: " + id + " not found"));
        if(doctor.is_active()){
            doctor.set_active(false);
            doctorRepository.save(doctor);
            scheduleService.deleteByDoctorId(id);
            eventsService.sendEvent(new DoctorDeletedEvent(doctor.getId()));
        }
        else {
            throw new RepeatedActionException("You can't inactivate inactive doctor");
        }
    }

    public void activeDoctor(String id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor with id: " + id + " not found"));

        DoctorTopicResponse response = DoctorTopicResponse.builder()
                .id(doctor.getId())
                .phone(doctor.getPhone())
                .build();

        if(!doctor.is_active()){
            doctor.set_active(true);
            doctorRepository.save(doctor);
            eventsService.sendEvent(new DoctorCreatedEvent(doctor.getId(), doctor.getPhone()));
        }
        else{
            throw new RepeatedActionException("You can't activate active doctor!");
        }
    }


    public void delete(String id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor with id: " + id + " not found"));
        if(!doctor.isDeleted()){
            doctor.setDeleted(true);
            doctorRepository.save(doctor);
            scheduleService.deleteByDoctorId(id);
            eventsService.sendEvent(new DoctorDeletedEvent(doctor.getId()));
        }
        else {
            throw new RepeatedActionException("You can't delete deleted doctor!");
        }
    }

    public void restoreDoctor(String id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor with id: " + id + " not found"));
        if(doctor.isDeleted()){
            doctor.setDeleted(false);
            doctorRepository.save(doctor);
            DoctorTopicResponse response = DoctorTopicResponse.builder()
                    .id(doctor.getId())
                    .phone(doctor.getPhone())
                    .build();

            eventsService.sendEvent(new DoctorCreatedEvent(doctor.getId(), doctor.getPhone()));
        }
        else {
            throw new RepeatedActionException("You can't restore not deleted doctor!");
        }
    }

    public List<DoctorResponse> searchByName(String name) {
        return doctorRepository.searchByName(name)
                .stream()
                .map(doctorMapper::toDoctorResponse)
                .collect(Collectors.toList());
    }

    public List<DoctorResponse> searchByPhone(String phone) {
        return doctorRepository.searchByPhone(phone)
                .stream()
                .map(doctorMapper::toDoctorResponse)
                .collect(Collectors.toList());
    }

    public DoctorResponse findByEmail(String email) {
        return doctorRepository.findDoctorByEmail(email)
                .map(doctorMapper::toDoctorResponse)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor with this email is not found!"));
    }

    public DoctorResponse findByPhone(String phone) {
        return doctorRepository.findDoctorByPhone(phone)
                .map(doctorMapper::toDoctorResponse)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor with this phone number is not found!"));
    }

    public List<DoctorResponse> getByGender(String gender) {
        if (gender.equals("MALE") || gender.equals("FEMALE")) {
            Gender g = gender.equals("MALE") ? Gender.MALE : Gender.FEMALE;
            return doctorRepository.getDoctorByGender(g)
                    .stream()
                    .map(doctorMapper::toDoctorResponse)
                    .collect(Collectors.toList());
        }else{
            throw new NotValidGenderTypeException("Gender type not supported");
        }
    }
}