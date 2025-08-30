package com.maxvpire.rates.AppointmentRates;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatesService {
    @KafkaListener(topics = "appointment-topic", groupId = "my-group")
    public void consume() {
        System.out.println("Received: !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
}
