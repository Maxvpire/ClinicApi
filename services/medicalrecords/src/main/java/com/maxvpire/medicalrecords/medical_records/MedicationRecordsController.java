package com.maxvpire.medicalrecords.medical_records;

import com.maxvpire.medicalrecords.medical_records.dto.MedicationRecordRequest;
import com.maxvpire.medicalrecords.medical_records.dto.MedicationRecordUpdateRequest;
import com.maxvpire.medicalrecords.medical_records.dto.MedicationRecordsWithPrescriptionsResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/medication/records")
@RequiredArgsConstructor
public class MedicationRecordsController {
     private final MedicationRecordsService medicationRecordsService;

    @GetMapping("/all")
    public ResponseEntity<List<MedicationRecordsWithPrescriptionsResponse>> getAllMedicationRecords(){
        return ResponseEntity.ok(medicationRecordsService.findAll());
    }

    @PutMapping("/fill/{id}")
    public ResponseEntity<String> fillMedicationRecords(@PathVariable String id, @Valid @RequestBody MedicationRecordRequest request){
        medicationRecordsService.fillMedicalRecord(id, request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<MedicationRecordsWithPrescriptionsResponse> getMedicationRecordById(@PathVariable String id){
        return ResponseEntity.ok(medicationRecordsService.findById(id));
    }

    @GetMapping("/patient/id/{id}")
    public ResponseEntity<List<MedicationRecordsWithPrescriptionsResponse>> getMedicationRecordsByPatientId(@PathVariable String id){
        return ResponseEntity.ok(medicationRecordsService.findByPatientId(id));
    }

    @GetMapping("/doctor/id/{id}")
    public ResponseEntity<List<MedicationRecordsWithPrescriptionsResponse>> getMedicationRecordsByDoctorId(@PathVariable String id){
        return ResponseEntity.ok(medicationRecordsService.findByDoctorId(id));
    }

    @GetMapping("/appointment/id/{id}")
    public ResponseEntity<MedicationRecordsWithPrescriptionsResponse> getMedicationRecordsByAppointmentId(@PathVariable String id){
        return ResponseEntity.ok(medicationRecordsService.findByAppointmentId(id));
    }

    @GetMapping("/date")
    public ResponseEntity<List<MedicationRecordsWithPrescriptionsResponse>> findMedicationRecordsByDate(
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime to
    ){
        return ResponseEntity.ok(medicationRecordsService.findByDate(from, to));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateMedicationRecord(
            @PathVariable String id,
            @RequestBody MedicationRecordUpdateRequest request
    ) {
        medicationRecordsService.update(id, request);
        return ResponseEntity.accepted().build();
    }
}