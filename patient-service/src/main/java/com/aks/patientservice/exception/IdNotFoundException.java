package com.aks.patientservice.exception;

import java.util.UUID;

public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(String message, UUID id) {
        super(message);
    }
}
