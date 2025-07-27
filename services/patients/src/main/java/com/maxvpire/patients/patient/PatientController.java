package com.maxvpire.patients.patient;

import com.maxvpire.patients.patient.dto.PatientRequest;
import com.maxvpire.patients.patient.dto.PatientResponse;
import com.maxvpire.patients.patient.dto.UpdatePatientRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patient")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @PostMapping()
    public ResponseEntity<String> createPatient(@RequestBody @Valid PatientRequest request) {
        return ResponseEntity.ok(patientService.createPatient(request));
    }
//
//    @GetMapping("/search/{name}")
//    public ResponseEntity<List<PatientResponse>> searchPatients(@PathVariable("name") String name) {
//        return ResponseEntity.ok(patientService.searchPatient(name));
//    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePatient(
            @RequestBody @Valid UpdatePatientRequest request,
            @PathVariable String id
    ) {
        patientService.updatePatient(id, request);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/ban/{id}")
    public ResponseEntity<String> banPatient(@PathVariable String id) {
        patientService.banPatient(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/unban/{id}")
    public ResponseEntity<String> unbanPatient(@PathVariable String id) {
        patientService.unBanPatient(id);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<PatientResponse>> findAll() {
        return ResponseEntity.ok(patientService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PatientResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(patientService.findById(id));
    }

//    @GetMapping("/number/{number}")
//    public ResponseEntity<PatientResponse> findByNumber(@PathVariable String number) {
//        return ResponseEntity.ok(patientService.findByPhoneNumber(number));
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable String id) {
        patientService.deletePatient(id);
        return ResponseEntity.accepted().build();
    }
}
