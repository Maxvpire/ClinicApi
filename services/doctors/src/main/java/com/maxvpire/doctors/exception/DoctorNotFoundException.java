package com.maxvpire.doctors.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
@Data
public class DoctorNotFoundException extends RuntimeException {
    private final String message;
}
