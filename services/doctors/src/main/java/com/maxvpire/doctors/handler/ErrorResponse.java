package com.maxvpire.doctors.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
