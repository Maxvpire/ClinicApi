package com.maxvpire.patients.patient.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientCreatedEvent implements BaseEvent {
    private String id;
    private String phone;

    public String getEventType() {
        return "PATIENT_CREATED";
    }
}
