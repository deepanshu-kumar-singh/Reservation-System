package com.example.travel.adapter.strategy;

import com.example.travel.adapter.dto.FlightBookingRequest;
import com.example.travel.adapter.exception.ValidationException;
import com.example.travel.adapter.service.BookingValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component; // Removed @AllArgsConstructor

// @AllArgsConstructor // No longer needed if constructor is written manually

@Component
public class DefaultBookingValidationStrategy implements BookingValidationStrategy {

    private final BookingValidationService validator; // Made final to ensure initialization

    public DefaultBookingValidationStrategy(BookingValidationService validator) {
        this.validator = validator;
    }

    @Override
    public void validate(FlightBookingRequest request) throws ValidationException {
        try {
            validator.validate(request);
        } catch (IllegalArgumentException e) {
            throw new ValidationException(e.getMessage());
        }
    }
}
