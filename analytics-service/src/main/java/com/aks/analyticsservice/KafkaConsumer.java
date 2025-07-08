package com.aks.analyticsservice;

import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

import java.util.Arrays;

@Service
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "patient",groupId = "analytics-service")
    public void consumerEvent(byte[] event) {
        try {
            PatientEvent patientEvent = PatientEvent.parseFrom(event);
            log.info(patientEvent.toString());
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
//        log.info(Arrays.toString(event));

    }
}
