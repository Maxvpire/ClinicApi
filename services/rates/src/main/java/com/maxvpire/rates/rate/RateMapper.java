package com.maxvpire.rates.rate;

import com.maxvpire.rates.rate.dto.RateResponse;
import org.springframework.stereotype.Service;

@Service
public class RateMapper {
    public RateResponse toRateResponse(Rate rate) {
        return new  RateResponse(
                rate.getId(),
                rate.getAppointmentId(),
                rate.getPatientId(),
                rate.getDoctorId(),
                rate.getRating(),
                rate.getComment(),
                rate.isRated(),
                rate.getCreatedAt(),
                rate.getUpdatedAt()
        );
    }
}
