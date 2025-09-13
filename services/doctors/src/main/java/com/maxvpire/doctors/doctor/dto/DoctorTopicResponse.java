package com.maxvpire.doctors.doctor.dto;

import lombok.Builder;

@Builder
public record DoctorTopicResponse(
        String id,
        String phone
) {
}
