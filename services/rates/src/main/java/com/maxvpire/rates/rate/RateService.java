package com.maxvpire.rates.rate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxvpire.rates.exception.BadRequestException;
import com.maxvpire.rates.exception.RateNotFoundException;
import com.maxvpire.rates.rate.dto.DoctorOverageResponse;
import com.maxvpire.rates.rate.dto.GetRateRequest;
import com.maxvpire.rates.rate.dto.RateRequest;
import com.maxvpire.rates.rate.dto.RateResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RateService {
    private final RateRepository rateRepository;
    private final RateMapper rateMapper;

    @KafkaListener(topics = "appointments", groupId = "my-consumer-group", containerFactory = "kafkaListenerContainerFactory")
    public String listen(ConsumerRecord<String, Object> message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = message.value().toString();
            RateRequest rateRequest = mapper.readValue(json, RateRequest.class);
            return create(rateRequest);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String create(RateRequest rateRequest) {
        Rate rate = Rate.builder()
                .appointmentId(rateRequest.getId())
                .patientId(rateRequest.getPatientId())
                .doctorId(rateRequest.getDoctorId())
                .rating(0.0)
                .comment("")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        return rateRepository.save(rate).getId();
    }

    public List<RateResponse> findAll() {
        return rateRepository.findAll()
                .stream()
                .map(rateMapper::toRateResponse)
                .collect(Collectors.toList());
    }

    public RateResponse findById(String id) {
        return rateRepository.findById(id)
                .map(rateMapper::toRateResponse)
                .orElseThrow(() -> new RateNotFoundException("Rate not found with id: " + id));
    }

    public DoctorOverageResponse overageRateOfDoctors(String doctorId) {
        List<Rate> rates = rateRepository.findRatesByDoctorId(doctorId);
        if(rates.isEmpty()) {
            throw new BadRequestException("No rate found for doctor's rate: ");
        }
        else {
            double overall = 0;
            double overallRate = 0;
            for (Rate rate : rates) {
                overall += rate.getRating();
            }
            overallRate = overall / rates.size();
            return DoctorOverageResponse.builder()
                    .rating(overallRate)
                    .build();
        }
    }


    public void toRate(String id, @Valid GetRateRequest request) {
        Rate rate = rateRepository.findById(id)
                .orElseThrow(() -> new RateNotFoundException("Rate not found with id: " + id));
        if(rate.getRating() == 0.0 && rate.getComment().isEmpty()){
            rate.setRating(request.rating());
            rate.setComment(request.comment());
            rate.setRated(true);
            rateRepository.save(rate);
        }
        else{
            throw new BadRequestException("You can not fill the filled rate!");
        }
    }


    public RateResponse findByAppointmentId(String id) {
        return rateRepository.findRateByAppointmentId(id)
                .map(rateMapper::toRateResponse)
                .orElseThrow(() -> new RateNotFoundException("Rate not found with appointment id: " + id));
    }


    public List<RateResponse> findByPatientId(String id) {
        return rateRepository.findRatesByPatientId(id)
                .stream()
                .map(rateMapper::toRateResponse)
                .collect(Collectors.toList());
    }


    public List<RateResponse> findByDoctorId(String id) {
        return rateRepository.findRatesByDoctorId(id)
                .stream()
                .map(rateMapper::toRateResponse)
                .collect(Collectors.toList());
    }

    public List<RateResponse> getAllUnrated() {
        return rateRepository.findRatesByRated(false)
                .stream()
                .map(rateMapper::toRateResponse)
                .collect(Collectors.toList());
    }
}