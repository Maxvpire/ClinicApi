package com.maxvpire.patients.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
