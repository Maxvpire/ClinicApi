package com.maxvpire.patients.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PatientNotFoundException extends RuntimeException {
    private final String message;
}
