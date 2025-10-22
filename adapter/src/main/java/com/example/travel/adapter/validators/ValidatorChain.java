package com.example.travel.adapter.validators;

import com.example.travel.adapter.dto.FlightBookingRequest;
import com.example.travel.adapter.service.BookingValidator;

import java.util.ArrayList;
import java.util.List;

public class ValidatorChain {
    private final List<BookingValidator> validators = new ArrayList<>();

    public void addValidator(BookingValidator validator) {
        validators.add(validator);
    }

    public void validateAll(FlightBookingRequest request) {
        for (BookingValidator validator : validators) {
            validator.validate(request);
        }
    }
}
