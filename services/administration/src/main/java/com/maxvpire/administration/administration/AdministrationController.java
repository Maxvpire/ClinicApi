package com.maxvpire.administration.administration;

import com.maxvpire.administration.administration.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/administrations")
@RequiredArgsConstructor
public class AdministrationController {
    private final AdministrationService administrationService;

    @GetMapping("/all")
    public ResponseEntity<List<AdministrationResponse>> findAll() {
        return ResponseEntity.ok(administrationService.findAll());
    }

    @PostMapping()
    public ResponseEntity<String> create(@Valid @RequestBody AdministrationRequest request) {
        return ResponseEntity.ok(administrationService.create(request));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<AdministrationResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(administrationService.findById(id));
    }

    @GetMapping("/search/name/{name}")
    public ResponseEntity<List<AdministrationResponse>> searchByName(@PathVariable String name) {
        return ResponseEntity.ok(administrationService.searchByName(name));
    }

    @GetMapping("/search/phone/{number}")
    public ResponseEntity<List<AdministrationResponse>> searchByPhone(@PathVariable String number) {
        return ResponseEntity.ok(administrationService.searchByPhone(number));
    }

    @GetMapping("/phone/{number}")
    public ResponseEntity<AdministrationResponse> findByPhone(@PathVariable String number) {
        return ResponseEntity.ok(administrationService.findByPhoneNumber(number));
    }

    @GetMapping("/gender/{gender}")
    public ResponseEntity<List<AdministrationResponse>> searchByGender(@PathVariable String gender) {
        return ResponseEntity.ok(administrationService.getByGender(gender));
    }

    @GetMapping("/active/{id}")
    public ResponseEntity<AdministrationActiveResponse> isActive(@PathVariable String id) {
        return ResponseEntity.ok(administrationService.isActive(id));
    }

    @GetMapping("/deleted/{id}")
    public ResponseEntity<AdministrationDeletedResponse> isDeleted(@PathVariable String id) {
        return ResponseEntity.ok(administrationService.isDeleted(id));
    }

    @GetMapping("/all/active")
    public ResponseEntity<List<AdministrationResponse>> findAllActive() {
        return ResponseEntity.ok(administrationService.getActiveAdministrations());
    }

    @GetMapping("/all/inactive")
    public ResponseEntity<List<AdministrationResponse>> findAllInactive() {
        return ResponseEntity.ok(administrationService.getInactiveAdministrations());
    }

    @PutMapping("/inactive/{id}")
    public ResponseEntity<String> inActive(@PathVariable String id) {
        this.administrationService.inActive(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/active/{id}")
    public ResponseEntity<String> active(@PathVariable String id) {
        this.administrationService.active(id);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        this.administrationService.deleteAdministration(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity<String> restore(@PathVariable String id) {
        this.administrationService.restoreAdministration(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable String id, @RequestBody AdministrationUpdateRequest request) {
        this.administrationService.update(id, request);
        return ResponseEntity.accepted().build();
    }
}