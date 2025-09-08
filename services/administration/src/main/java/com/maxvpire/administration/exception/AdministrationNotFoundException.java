package com.maxvpire.administration.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
@Data
public class AdministrationNotFoundException extends RuntimeException {
    private final String message;
}
