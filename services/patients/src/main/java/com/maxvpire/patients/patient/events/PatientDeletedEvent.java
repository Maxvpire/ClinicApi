package com.maxvpire.patients.patient.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientDeletedEvent implements BaseEvent{
    private String id;

    public String getEventType() {
        return "PATIENT_DELETED";
    }
}
