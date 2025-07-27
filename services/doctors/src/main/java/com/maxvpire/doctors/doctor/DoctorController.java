package com.maxvpire.doctors.doctor;

import com.maxvpire.doctors.doctor.dto.DoctorRequest;
import com.maxvpire.doctors.doctor.dto.DoctorResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctor")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<String> createDoctor(@RequestBody @Valid DoctorRequest request) {
        return ResponseEntity.ok(doctorService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponse>> getDoctor() {
        return ResponseEntity.ok(doctorService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<DoctorResponse> getDoctorById(@PathVariable String id) {
        return ResponseEntity.ok(doctorService.findById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateDoctor(
            @PathVariable String id,
            @RequestBody @Valid DoctorRequest request
    ) {
        this.doctorService.updateDoctor(id, request);
        return ResponseEntity.accepted().build();
    }
}
