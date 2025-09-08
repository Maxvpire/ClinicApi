package com.maxvpire.rates.rate.dto;

import lombok.Builder;

@Builder
public record DoctorOverageResponse(
        double rating
) {
}
