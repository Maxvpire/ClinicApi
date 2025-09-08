package com.maxvpire.appointments.handler;

import java.util.Map;

public record ErrorResponse (
    Map<String, String> errors
){}
