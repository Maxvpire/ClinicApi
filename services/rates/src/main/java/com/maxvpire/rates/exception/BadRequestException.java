package com.maxvpire.rates.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
@Data
public class BadRequestException extends RuntimeException {
    private final String message;
}
