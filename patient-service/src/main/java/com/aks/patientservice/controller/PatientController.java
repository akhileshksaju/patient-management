package com.aks.patientservice.controller;

import com.aks.patientservice.DTO.PatientRequestDTO;
import com.aks.patientservice.DTO.PatientResponseDTO;
import com.aks.patientservice.DTO.validator.CreatePatientValidatingGroup;
import com.aks.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
@Tag(name = "Patient",description = "managind patients")
public class PatientController {

    private final PatientService patientService;


    @GetMapping
    @Operation(summary = "Get all employess using paging")
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {

        return ResponseEntity.ok().body(patientService.getAllPatients());

    }

    @PostMapping
    @Operation(summary = "Creat a patient")
    public ResponseEntity<PatientResponseDTO> createPatient(@Validated({Default.class, CreatePatientValidatingGroup.class }) @RequestBody PatientRequestDTO patientRequestDTO) {
        return ResponseEntity.ok().body(patientService.createPatient(patientRequestDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a patient")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id, @Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO) {
        return ResponseEntity.ok().body(patientService.updatePatient(id, patientRequestDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a patient")
    public ResponseEntity<PatientResponseDTO> deletePatient(@PathVariable UUID id) {
        return ResponseEntity.accepted().body(patientService.deletePatient(id));
    }


}
