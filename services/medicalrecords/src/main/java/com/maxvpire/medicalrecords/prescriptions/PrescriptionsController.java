package com.maxvpire.medicalrecords.prescriptions;

import com.maxvpire.medicalrecords.prescriptions.dto.PrescriptionUpdateRequest;
import com.maxvpire.medicalrecords.prescriptions.dto.PrescriptionWithMedicationRecordsResponse;
import com.maxvpire.medicalrecords.prescriptions.dto.PrescriptionsRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/prescriptions")
@RequiredArgsConstructor
public class PrescriptionsController {
    private final PrescriptionsService prescriptionsService;

    @PostMapping()
    public ResponseEntity<String> create(@Valid @RequestBody PrescriptionsRequest request) {
        return ResponseEntity.ok(prescriptionsService.create(request));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PrescriptionWithMedicationRecordsResponse>> findAll() {
        return ResponseEntity.ok(prescriptionsService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PrescriptionWithMedicationRecordsResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(prescriptionsService.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<PrescriptionWithMedicationRecordsResponse>> searchByMedicationName(@RequestParam String name) {
        return ResponseEntity.ok(prescriptionsService.search(name));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<String> update(
            @PathVariable String id,
            @RequestBody PrescriptionUpdateRequest request
    ) {
        return ResponseEntity.accepted().build();
    }
}