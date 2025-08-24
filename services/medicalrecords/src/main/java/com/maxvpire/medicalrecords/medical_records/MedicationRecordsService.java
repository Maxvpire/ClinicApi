package com.maxvpire.medicalrecords.medical_records;

import com.maxvpire.medicalrecords.medical_records.dto.MedicationRecordRequest;
import com.maxvpire.medicalrecords.medical_records.dto.MedicationRecordUpdateRequest;
import com.maxvpire.medicalrecords.medical_records.dto.MedicationRecordsWithPrescriptionsResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicationRecordsService {
    private final MedicalRecordsRepository medicationRecordsRepository;
    private final MedicationRecordsMapper medicationRecordsMapper;

    public List<MedicationRecordsWithPrescriptionsResponse> findAll() {
        return medicationRecordsRepository.findAll()
                .stream()
                .map(medicationRecordsMapper::toMedicationRecords)
                .collect(Collectors.toList());
    }


    public String create(MedicationRecordRequest request) {
        MedicalRecords medicalRecords = MedicalRecords.builder()
                .patientId(request.patientId())
                .doctorId(request.doctorId())
                .appointmentId(request.appointmentId())
                .diagnosis(request.diagnosis())
                .treatment(request.treatment())
                .build();
        return medicationRecordsRepository.save(medicalRecords).getId();
    }

    public MedicationRecordsWithPrescriptionsResponse findById(String id) {
        return medicationRecordsRepository.findById(id)
                .map(medicationRecordsMapper::toMedicationRecords)
                .orElseThrow(() -> new RuntimeException("Medication records with id " + id + " not found!"));
    }

    public List<MedicationRecordsWithPrescriptionsResponse> findByPatientId(String id) {
        return medicationRecordsRepository.findByPatientId(id)
                .stream()
                .map(medicationRecordsMapper::toMedicationRecords)
                .collect(Collectors.toList());
    }


    public List<MedicationRecordsWithPrescriptionsResponse> findByDoctorId(String id) {
        return medicationRecordsRepository.findByDoctorId(id)
                .stream()
                .map(medicationRecordsMapper::toMedicationRecords)
                .collect(Collectors.toList());
    }

    public MedicationRecordsWithPrescriptionsResponse findByAppointmentId(String id) {
        return medicationRecordsRepository.findByAppointmentId(id)
                .map(medicationRecordsMapper::toMedicationRecords)
                .orElseThrow(() -> new RuntimeException("Medication records with appointment id " + id + " not found!"));
    }


    public List<MedicationRecordsWithPrescriptionsResponse> findByDate(LocalDateTime from, LocalDateTime to) {
        return medicationRecordsRepository.findByCreatedAtBetween(from, to)
                .stream()
                .map(medicationRecordsMapper::toMedicationRecords)
                .collect(Collectors.toList());
    }

    public void update(String id, MedicationRecordUpdateRequest request) {
        MedicalRecords medicalRecords = medicationRecordsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medication record with id " + id + " not found!"));

        mergeRecord(medicalRecords, request);
        medicationRecordsRepository.save(medicalRecords);
    }

    private void mergeRecord(MedicalRecords medicalRecords, MedicationRecordUpdateRequest request) {
        if(StringUtils.isNotBlank(request.diagnosis())){
            medicalRecords.setDiagnosis(request.diagnosis());
        }
        if (StringUtils.isNotBlank(request.treatment())){
            medicalRecords.setTreatment(request.treatment());
        }
    }
}
