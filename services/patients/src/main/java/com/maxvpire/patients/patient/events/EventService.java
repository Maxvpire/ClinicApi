package com.maxvpire.patients.patient.events;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {
    private final KafkaTemplate<String, EventMessage> kafkaTemplate;
    private static final String TOPIC = "patients";

    public void sendEvent(BaseEvent event) {
        EventMessage message = new EventMessage(event.getEventType(), event);
        kafkaTemplate.send(TOPIC, message);
    }
}
