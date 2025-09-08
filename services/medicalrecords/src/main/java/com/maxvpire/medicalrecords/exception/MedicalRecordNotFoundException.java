package com.maxvpire.medicalrecords.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
@Data
public class MedicalRecordNotFoundException extends RuntimeException {
    private final String message;
}
