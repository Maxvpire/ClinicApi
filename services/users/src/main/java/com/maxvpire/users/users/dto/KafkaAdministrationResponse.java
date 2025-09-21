package com.maxvpire.users.users.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KafkaAdministrationResponse {
    private String id;
    private String phone;
}
