package com.maxvpire.medicalrecords.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
