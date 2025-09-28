package com.maxvpire.patients.patient;

import com.maxvpire.patients.exception.PatientNotFoundException;
import com.maxvpire.patients.exception.RepeatedActionException;
import com.maxvpire.patients.patient.dto.PatientKafkaResponse;
import com.maxvpire.patients.patient.dto.PatientRequest;
import com.maxvpire.patients.patient.dto.PatientResponse;
import com.maxvpire.patients.patient.dto.UpdatePatientRequest;
import com.maxvpire.patients.patient.events.EventService;
import com.maxvpire.patients.patient.events.PatientCreatedEvent;
import com.maxvpire.patients.patient.events.PatientDeletedEvent;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final EventService eventService;

    public String createPatient(PatientRequest request) {
        Patient patient = patientRepository.save(patientMapper.toPatient(request));

        PatientCreatedEvent event = PatientCreatedEvent.builder()
                .id(patient.getId())
                .phone(patient.getPhone())
                .build();

        eventService.sendEvent(event);

        return patient.getId();
    }

    public void updatePatient(String id, UpdatePatientRequest request) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found!"));
        mergerPatient(patient, request);
        patientRepository.save(patient);
    }

    private void mergerPatient(Patient patient, UpdatePatientRequest request) {
        if(StringUtils.isNotBlank((request.firstname()))) {
            patient.setFirstname(request.firstname());
        }
        if(StringUtils.isNotBlank((request.lastname()))) {
            patient.setLastname(request.lastname());
        }
        if(StringUtils.isNotBlank(request.phone())){
            patient.setPhone(request.phone());
        }
        if (request.address() != null) {
            patient.setAddress(request.address());
        }
    }


    public List<PatientResponse> findAll() {
        return patientRepository.findAll()
                .stream()
                .map(patientMapper::fromPatient)
                .collect(Collectors.toList());
    }

    public PatientResponse findById(String id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found!"));
        return patientMapper.fromPatient(patient);
    }

    public void deletePatient(String id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found!"));

        if(!patient.isDeleted()){
            patient.setDeleted(true);
        }
        else {
            throw new RepeatedActionException("You can't delete deleted patient!");
        }
        patientRepository.save(patient);

        PatientDeletedEvent event = PatientDeletedEvent.builder()
                .id(patient.getId())
                .build();

        eventService.sendEvent(event);
    }

    public void restorePatient(String id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found!"));

        if(patient.isDeleted()){
            patient.setDeleted(false);
        }
        else {
            throw new RepeatedActionException("You can't restore not deleted patient!");
        }
        patientRepository.save(patient);
        PatientCreatedEvent event = PatientCreatedEvent.builder()
                .id(patient.getId())
                .phone(patient.getPhone())
                .build();

        eventService.sendEvent(event);
    }

    public List<PatientResponse> searchPatientByName(String name) {
        return patientRepository.searchByName(name)
                .stream()
                .map(patientMapper::fromPatient)
                .collect(Collectors.toList());
    }

    public PatientResponse findByPhoneNumber(String phoneNumber) {
        Patient patient = patientRepository.findPatientByPhone(phoneNumber)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found!"));
        return patientMapper.fromPatient(patient);
    }

    public void banPatient(String id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found!"));
        if(!patient.isBanned()) {
            patient.setBanned(true);
        }
        else {
            throw new RepeatedActionException("You can't ban banned patient!");
        }
        patientRepository.save(patient);
        PatientDeletedEvent event = PatientDeletedEvent.builder()
                .id(patient.getId())
                .build();

        eventService.sendEvent(event);
    }

    public void unBanPatient(String id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found!"));
        if(patient.isBanned()) {
            patient.setBanned(false);
        }
        else {
            throw new RepeatedActionException("You can't unban unbanned patient!");
        }
        patientRepository.save(patient);
        PatientCreatedEvent event = PatientCreatedEvent.builder()
                .id(patient.getId())
                .phone(patient.getPhone())
                .build();

        eventService.sendEvent(event);
    }

    public List<PatientResponse> searchPatientByPhoneNumber(String phone) {
        return patientRepository.searchByPhone(phone)
                .stream()
                .map(patientMapper::fromPatient)
                .collect(Collectors.toList());
    }
}
