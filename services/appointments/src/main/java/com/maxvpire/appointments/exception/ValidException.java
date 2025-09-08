package com.maxvpire.appointments.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ValidException extends RuntimeException {
    private final String message;
}
