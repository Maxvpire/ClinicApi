package com.maxvpire.patients.patient;

import com.maxvpire.patients.exception.PatientNotFoundException;
import com.maxvpire.patients.patient.dto.PatientRequest;
import com.maxvpire.patients.patient.dto.PatientResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public String createPatient(PatientRequest request) {
        Patient patient = patientRepository.save(patientMapper.toPatient(request));
        return patient.getId();
    }

    public void updatePatient(String id, PatientRequest request) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found!"));
        mergerPatient(patient, request);
        patientRepository.save(patient);
    }

    private void mergerPatient(Patient patient, PatientRequest request) {
        if(StringUtils.isNotBlank((request.firstname()))) {
            patient.setFirstname(request.firstname());
        }
        if(StringUtils.isNotBlank((request.lastname()))) {
            patient.setLastname(request.lastname());
        }
        if(StringUtils.isNotBlank(request.gender().toString())){
            patient.setGender(request.gender());
        }
        if(StringUtils.isNotBlank(request.birth_date().toString())){
            patient.setBirth_date(request.birth_date());
        }
        if(StringUtils.isNotBlank(request.phone_number())){
            patient.setPhone_number(request.phone_number());
        }
        if(StringUtils.isNotBlank(request.email())){
            patient.setEmail(request.email());
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
        patientRepository.deleteById(id);
    }

    public List<PatientResponse> searchPatient(String name) {
        return patientRepository.searchPatients(name)
                .stream()
                .map(patientMapper::fromPatient)
                .collect(Collectors.toList());
    }

    public PatientResponse findByPhoneNumber(String phoneNumber) {
        Patient patient = patientRepository.findPatientByPhone_number(phoneNumber)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found!"));
        return patientMapper.fromPatient(patient);
    }

    public void banPatient(String id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found!"));
        patient.setBanned(true);
        patientRepository.save(patient);
    }

    public void unBanPatient(String id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found!"));
        patient.setBanned(false);
        patientRepository.save(patient);
    }
}
