package com.maxvpire.appointments.appointment.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventMessage {
    private String eventType;
    private Object payload;
}
