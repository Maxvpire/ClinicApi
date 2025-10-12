package com.maxvpire.payment.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class InvoicesNotFoundException extends RuntimeException {
    private final String message;
}
