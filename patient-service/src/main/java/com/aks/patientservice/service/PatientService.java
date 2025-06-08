package com.aks.patientservice.service;

import com.aks.patientservice.DTO.PatientRequestDTO;
import com.aks.patientservice.DTO.PatientResponseDTO;
import com.aks.patientservice.exception.EmailAlreadyExistException;
import com.aks.patientservice.exception.IdNotFoundException;
import com.aks.patientservice.mapper.PatientMapper;
import com.aks.patientservice.model.Patient;
import com.aks.patientservice.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PatientService {

    Pageable pageable = PageRequest.of(0, 10, Sort.by("name"));

    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getAllPatients() {
         return PatientMapper.patientToDto(patientRepository.findAll(pageable).getContent());
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {

        if(patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            log.error("Email {} is already exist in the database",patientRequestDTO.getEmail());
            throw  new EmailAlreadyExistException("this is email is already exist");
        }
        Patient patient = patientRepository.save(PatientMapper.dtoToPatient(patientRequestDTO));

        return PatientMapper.patientToDto(patient);



    }

    public PatientResponseDTO updatePatient(UUID id,PatientRequestDTO patientRequestDTO) {
        Patient patient = patientRepository.findById(id).orElseThrow(()-> new IdNotFoundException("cannot found this id {}",id));
        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        if (patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(),id)) {
            log.error("Email {} is already exist in the database",patient.getEmail());
            throw  new EmailAlreadyExistException("this is email is already exist");
        }
        patient.setEmail(patientRequestDTO.getEmail());
        patient=patientRepository.save(patient);
        return PatientMapper.patientToDto(patient);

    }

    public PatientResponseDTO deletePatient(UUID id) {
        PatientResponseDTO patientResponseDTO=null;
        if(patientRepository.existsById(id)) {
            patientResponseDTO=PatientMapper.patientToDto(patientRepository.getReferenceById(id));
            patientRepository.deleteById(id);
            return patientResponseDTO;

        }
        else {
            throw new IdNotFoundException("User with this {} id is not exist" ,id);
        }
    }
}
