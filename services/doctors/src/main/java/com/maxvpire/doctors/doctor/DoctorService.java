package com.maxvpire.doctors.doctor;

import com.maxvpire.doctors.doctor.dto.DoctorRequest;
import com.maxvpire.doctors.doctor.dto.DoctorResponse;
import com.maxvpire.doctors.schedule.ScheduleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final ScheduleService scheduleService;

    public String create(DoctorRequest request) {
        Doctor doctor = Doctor.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .phone(request.phone())
                .dateofbirth(request.dateofbirth())
                .specialization(request.specialization())
                .gender(request.gender())
                .build();
        return doctorRepository.save(doctor).getId();
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
                .orElseThrow(() -> new EntityNotFoundException("Doctor with id: " + id + " not found"));
    }


    public void updateDoctor(String id, DoctorRequest request) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doctor with id: " + id + " not found"));
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
                .orElseThrow(() -> new EntityNotFoundException("Doctor with id: " + id + " not found"));
        doctor.set_active(false);
        doctorRepository.save(doctor);
        scheduleService.deleteByDoctorId(id);
    }

    public void activeDoctor(String id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doctor with id: " + id + " not found"));
        doctor.set_active(true);
        doctorRepository.save(doctor);
    }


    public void delete(String id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doctor with id: " + id + " not found"));
        doctor.setDeleted(true);
        doctorRepository.save(doctor);
        scheduleService.deleteByDoctorId(id);
    }

    public void restoreDoctor(String id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doctor with id: " + id + " not found"));
        doctor.setDeleted(false);
        doctorRepository.save(doctor);
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
                .orElseThrow(() -> new EntityNotFoundException("Doctor with this email is not found!"));
    }

    public DoctorResponse findByPhone(String phone) {
        return doctorRepository.findDoctorByPhone(phone)
                .map(doctorMapper::toDoctorResponse)
                .orElseThrow(() -> new EntityNotFoundException("Doctor with this phone number is not found!"));
    }
}