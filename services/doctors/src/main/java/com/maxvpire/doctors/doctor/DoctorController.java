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

    @GetMapping("/all")
    public ResponseEntity<List<DoctorResponse>> getDoctor() {
        return ResponseEntity.ok(doctorService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<DoctorResponse> getDoctorById(@PathVariable String id) {
        return ResponseEntity.ok(doctorService.findById(id));
    }

    @GetMapping("/search/name/{name}")
    public ResponseEntity<List<DoctorResponse>> searchDoctorByName(@PathVariable String name) {
        return ResponseEntity.ok(doctorService.searchByName(name));
    }

    @GetMapping("/search/phone/{phone}")
    public ResponseEntity<List<DoctorResponse>> searchDoctorsByPhone(@PathVariable String phone) {
        return ResponseEntity.ok(doctorService.searchByPhone(phone));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<DoctorResponse> getDoctorByEmail(@PathVariable String email) {
        return ResponseEntity.ok(doctorService.findByEmail(email));
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<DoctorResponse> getDoctorByPhone(@PathVariable String phone) {
        return ResponseEntity.ok(doctorService.findByPhone(phone));
    }

    @GetMapping("/gender/{gender}")
    public ResponseEntity<List<DoctorResponse>> getDoctorByGender(@PathVariable String gender) {
        return ResponseEntity.ok(doctorService.getByGender(gender));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateDoctor(
            @PathVariable String id,
            @RequestBody @Valid DoctorRequest request
    ) {
        this.doctorService.updateDoctor(id, request);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/inactive/{id}")
    public ResponseEntity<Void> inactiveDoctor(@PathVariable String id){
        this.doctorService.inActiveDoctor(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/active/{id}")
    public ResponseEntity<Void> activeDoctor(@PathVariable String id){
        this.doctorService.activeDoctor(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable String id){
        this.doctorService.delete(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity<Void> restoreDoctor(@PathVariable String id){
        this.doctorService.restoreDoctor(id);
        return ResponseEntity.accepted().build();
    }
}
