package com.maxvpire.medicalrecords.prescriptions;

import com.maxvpire.medicalrecords.exception.PrescriptionNotFoundException;
import com.maxvpire.medicalrecords.medical_records.MedicalRecords;
import com.maxvpire.medicalrecords.medical_records.MedicalRecordsRepository;
import com.maxvpire.medicalrecords.prescriptions.dto.PrescriptionUpdateRequest;
import com.maxvpire.medicalrecords.prescriptions.dto.PrescriptionWithMedicationRecordsResponse;
import com.maxvpire.medicalrecords.prescriptions.dto.PrescriptionsRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrescriptionsService {
    private final PrescriptionRepository prescriptionRepository;
    private final MedicalRecordsRepository medicalRecordsRepository;
    private final PrescriptionsMapper  prescriptionsMapper;
    public String create(PrescriptionsRequest request) {
        MedicalRecords medicalRecords = medicalRecordsRepository.findById(request.medical_records_id())
                .orElseThrow(() -> new PrescriptionNotFoundException("Medical records not found!"));

        Prescriptions prescriptions = Prescriptions.builder()
                .medicalRecords(medicalRecords)
                .medicationName(request.medicationName())
                .dosage(request.dosage())
                .frequency(request.frequency())
                .duration(request.duration())
                .build();
        return prescriptionRepository.save(prescriptions).getId();
    }


    public List<PrescriptionWithMedicationRecordsResponse> findAll() {
        return prescriptionRepository.findAll()
                .stream()
                .map(prescriptionsMapper::toPrescriptionWithMedicationRecordsResponse)
                .collect(Collectors.toList());
    }


    public PrescriptionWithMedicationRecordsResponse findById(String id) {
        return prescriptionRepository.findById(id)
                .map(prescriptionsMapper::toPrescriptionWithMedicationRecordsResponse)
                .orElseThrow(() -> new PrescriptionNotFoundException("Prescription not found!"));
    }


    public List<PrescriptionWithMedicationRecordsResponse> search(String name) {
        return prescriptionRepository.findByMedicationNameContaining(name)
                .stream()
                .map(prescriptionsMapper::toPrescriptionWithMedicationRecordsResponse)
                .collect(Collectors.toList());
    }

    public void update(String id, PrescriptionUpdateRequest request) {
        Prescriptions prescriptions = prescriptionRepository.findById(id)
                .orElseThrow(() ->  new PrescriptionNotFoundException("Prescription not found!"));

        mergePrescription(prescriptions, request);
        prescriptionRepository.save(prescriptions);
    }

    private void mergePrescription(Prescriptions prescriptions, PrescriptionUpdateRequest request) {
        if(StringUtils.isNotBlank(request.medicationName())) {
            prescriptions.setMedicationName(request.medicationName());
        }

        if(StringUtils.isNotBlank(request.dosage())) {
            prescriptions.setDosage(request.dosage());
        }

        if(StringUtils.isNotBlank(request.frequency())) {
            prescriptions.setFrequency(request.frequency());
        }

        if(StringUtils.isNotBlank(request.duration())) {
            prescriptions.setDuration(request.duration());
        }

    }

}
