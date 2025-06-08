package com.aks.patientservice.DTO;

import com.aks.patientservice.DTO.validator.CreatePatientValidatingGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientRequestDTO {

    @NotBlank(message = "name cannot be blank")
    @Size(max = 100,message = "name cannot exceed 100")
    private String name;

    @NotBlank(message = "email is mandatory")
    @Email(message = "email shoulg be valid")
    private String email;

    @NotBlank(message = "address cannot be blank")
    private String address;

    @NotBlank(message = "dob cannot be blank")
    private String dateOfBirth;

    @NotBlank(groups = CreatePatientValidatingGroup.class, message = "date cannot be blank")
    private String registeredDate;




}
