package com.aks.patientservice.mapper;

import com.aks.patientservice.DTO.PatientRequestDTO;
import com.aks.patientservice.DTO.PatientResponseDTO;
import com.aks.patientservice.model.Patient;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class PatientMapper {

    public static List<PatientResponseDTO> patientToDto(List<Patient> patient) {
        return patient.stream()
                .map(a ->new PatientResponseDTO(a.getId(),a.getName(),a.getEmail(),a.getAddress(),a.getDateOfBirth(),a.getRegisteredDate()))
                .collect(Collectors.toList());
    }

    public static Patient dtoToPatient(PatientRequestDTO patientRequestDTO) {
        Patient patient = new Patient();
        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        patient.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()));
        return patient;

    }

    public static PatientResponseDTO patientToDto(Patient patient) {
        PatientResponseDTO  patientResponseDTO;
        patientResponseDTO = new PatientResponseDTO(patient.getId(),patient.getName(),patient.getEmail(),patient.getAddress(),patient.getDateOfBirth(),LocalDate.now());
        return patientResponseDTO;
    }


}
