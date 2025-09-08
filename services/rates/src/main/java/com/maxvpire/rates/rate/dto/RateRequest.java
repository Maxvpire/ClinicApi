package com.maxvpire.rates.rate.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RateRequest {
    private String id;
    private String patientId;
    private String doctorId;
}
