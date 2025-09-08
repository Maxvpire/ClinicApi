package com.maxvpire.rates.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
