package com.maxvpire.doctors.doctor.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorCreatedEvent implements BaseEvent {
    private String id;
    private String phone;

    public String getEventType() {
        return "DOCTOR_CREATED";
    }
}
