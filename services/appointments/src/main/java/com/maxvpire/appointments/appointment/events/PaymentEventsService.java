package com.maxvpire.appointments.appointment.events;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentEventsService {
    private final KafkaTemplate<String, EventMessage> eventMessageKafkaTemplate;
    private static final String TOPIC = "payments";

    public void sendEvent(BaseEvent event) {
        EventMessage message = new EventMessage(event.getEventType(), event);
        eventMessageKafkaTemplate.send(TOPIC, message);
    }

}
