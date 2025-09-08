package com.maxvpire.appointments.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AppointmentNotFoundException extends RuntimeException {
    private final String message;
}
