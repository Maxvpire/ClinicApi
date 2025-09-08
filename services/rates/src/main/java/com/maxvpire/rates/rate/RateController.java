package com.maxvpire.rates.rate;

import com.maxvpire.rates.rate.dto.DoctorOverageResponse;
import com.maxvpire.rates.rate.dto.GetRateRequest;
import com.maxvpire.rates.rate.dto.RateResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rates")
@RequiredArgsConstructor
public class RateController {
    private final RateService rateService;

    @GetMapping("/all")
    public ResponseEntity<List<RateResponse>> getAllRates() {
        return ResponseEntity.ok(rateService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<RateResponse> getRateById(@PathVariable String id) {
        return ResponseEntity.ok(rateService.findById(id));
    }

    @GetMapping("/appointment/id/{id}")
    public ResponseEntity<RateResponse> getRateByAppointmentId(@PathVariable String id) {
        return ResponseEntity.ok(rateService.findByAppointmentId(id));
    }

    @GetMapping("/patient/id/{id}")
    public ResponseEntity<List<RateResponse>> getRateByPatientId(@PathVariable String id) {
        return ResponseEntity.ok(rateService.findByPatientId(id));
    }

    @GetMapping("/doctor/id/{id}")
    public ResponseEntity<List<RateResponse>> getRateByDoctorId(@PathVariable String id) {
        return ResponseEntity.ok(rateService.findByDoctorId(id));
    }

    @GetMapping("/doctor/overage/{id}")
    public ResponseEntity<DoctorOverageResponse> getOverageOfDoctors(@PathVariable String id) {
        return ResponseEntity.ok(rateService.overageRateOfDoctors(id));
    }

    @GetMapping("/unrated")
    public ResponseEntity<List<RateResponse>> getRateByRated() {
        return ResponseEntity.ok(rateService.getAllUnrated());
    }

    @PutMapping("/rate/{id}")
    public ResponseEntity<RateResponse> updateRate(@PathVariable String id, @Valid @RequestBody GetRateRequest request) {
        rateService.toRate(id, request);
        return ResponseEntity.accepted().build();
    }


}
