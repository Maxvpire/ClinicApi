package com.maxvpire.users.users.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KafkaPatientResponse {
    private String id;
    private String phone;
}
