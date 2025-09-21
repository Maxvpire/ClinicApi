package com.maxvpire.doctors.doctor.events;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventsService {
    private final KafkaTemplate<String, EventMessage> kafkaTemplate;
    private static final String TOPIC = "doctors";

    public void sendEvent(BaseEvent event) {
        EventMessage message = new EventMessage(event.getEventType(), event);
        kafkaTemplate.send(TOPIC, message);
    }
}
