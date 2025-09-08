package com.maxvpire.medicalrecords.medical_records;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxvpire.medicalrecords.exception.BadRequestException;
import com.maxvpire.medicalrecords.exception.KafkaErrorMessageException;
import com.maxvpire.medicalrecords.exception.MedicalRecordNotFoundException;
import com.maxvpire.medicalrecords.medical_records.dto.KafkaResponse;
import com.maxvpire.medicalrecords.medical_records.dto.MedicationRecordRequest;
import com.maxvpire.medicalrecords.medical_records.dto.MedicationRecordUpdateRequest;
import com.maxvpire.medicalrecords.medical_records.dto.MedicationRecordsWithPrescriptionsResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
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


    @KafkaListener(topics = "appointments", groupId = "my-consumer-group-2", containerFactory = "kafkaListenerContainerFactory")
    public String listen(ConsumerRecord<String, Object> message) {
        try {
            System.out.println("Consumer received message: " + message.value() + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            ObjectMapper mapper = new ObjectMapper();
            String json = message.value().toString();
            KafkaResponse kafkaResponse = mapper.readValue(json, KafkaResponse.class);
            return create(kafkaResponse);
        }
        catch (Exception e) {
            throw new KafkaErrorMessageException(e.getMessage());
        }
    }

    public String create(KafkaResponse request) {
        MedicalRecords medicalRecords = MedicalRecords.builder()
                .patientId(request.getPatientId())
                .doctorId(request.getDoctorId())
                .appointmentId(request.getId())
                .diagnosis("")
                .treatment("")
                .build();
        return medicationRecordsRepository.save(medicalRecords).getId();
    }

    public void fillMedicalRecord(String id, MedicationRecordRequest request) {
        MedicalRecords medicalRecords = medicationRecordsRepository.findById(id)
                .orElseThrow(() -> new MedicalRecordNotFoundException("Medical Record Not Found!"));

        if(medicalRecords.getTreatment().isEmpty() && medicalRecords.getDiagnosis().isEmpty()) {
            medicalRecords.setTreatment(request.treatment());
            medicalRecords.setDiagnosis(request.diagnosis());

            medicationRecordsRepository.save(medicalRecords);
        }

        else{
            throw new BadRequestException("You can't fill the filled medication record!");
        }
    }

    public MedicationRecordsWithPrescriptionsResponse findById(String id) {
        return medicationRecordsRepository.findById(id)
                .map(medicationRecordsMapper::toMedicationRecords)
                .orElseThrow(() -> new MedicalRecordNotFoundException("Medication records with id " + id + " not found!"));
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
                .orElseThrow(() -> new MedicalRecordNotFoundException("Medication records with appointment id " + id + " not found!"));
    }


    public List<MedicationRecordsWithPrescriptionsResponse> findByDate(LocalDateTime from, LocalDateTime to) {
        return medicationRecordsRepository.findByCreatedAtBetween(from, to)
                .stream()
                .map(medicationRecordsMapper::toMedicationRecords)
                .collect(Collectors.toList());
    }

    public void update(String id, MedicationRecordUpdateRequest request) {
        MedicalRecords medicalRecords = medicationRecordsRepository.findById(id)
                .orElseThrow(() -> new MedicalRecordNotFoundException("Medication record with id " + id + " not found!"));

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
