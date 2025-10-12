package com.maxvpire.appointments.appointment.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentCreatedEvent implements BaseEvent {
    private String patientId;
    private String appointmentId;
    private double amount;

    public String getEventType() {
        return "PAYMENT_CREATED";
    }
}
