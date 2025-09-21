package com.maxvpire.doctors.doctor.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDeletedEvent implements BaseEvent {
    private String id;

    public String getEventType() {
        return "DOCTOR_DELETED";
    }
}
