package com.maxvpire.users.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PasswordNotMatchException extends RuntimeException {
    private final String message;
}
