package com.maxvpire.users.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
