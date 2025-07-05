package com.aks.patientservice.kafka;

import com.aks.patientservice.model.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public void sendPatient(Patient patient){
        PatientEvent patientEvent=PatientEvent.newBuilder()
                .setPatientId(patient.getId().toString())
                .setName(patient.getName())
                .setEmail(patient.getEmail())
                .build();
        try{
            kafkaTemplate.send("patient", patientEvent.toByteArray());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
