package com.maxvpire.rates.rate.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record GetRateRequest(
        @NotNull
        @DecimalMin(value = "0.0", inclusive = false)
        @DecimalMax(value = "5.0", inclusive = false)
        double rating,

        @NotNull
        String comment
) { }