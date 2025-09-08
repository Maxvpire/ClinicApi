package com.maxvpire.rates.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RateNotFoundException extends RuntimeException {
    private final String message;
}
