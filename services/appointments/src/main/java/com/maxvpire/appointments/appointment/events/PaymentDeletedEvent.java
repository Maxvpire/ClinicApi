package com.maxvpire.appointments.appointment.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDeletedEvent implements BaseEvent {
    private String appointmentId;

    public String getEventType() {
        return "PAYMENT_DELETED";
    }
}
