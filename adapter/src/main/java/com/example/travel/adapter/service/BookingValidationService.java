package com.example.travel.adapter.service;

import com.example.travel.adapter.dto.FlightBookingRequest;
import com.example.travel.adapter.validators.*;

import org.springframework.stereotype.Service;

@Service
public class BookingValidationService {
    private final ValidatorChain validatorChain = new ValidatorChain();

    public BookingValidationService() {
        validatorChain.addValidator(new FirstNameValidator());
        validatorChain.addValidator(new LastNameValidator());
        validatorChain.addValidator(new PhoneValidator());
        validatorChain.addValidator(new FlightIdValidator());
        validatorChain.addValidator(new ClassOfServiceValidator());
    }

    public void validate(FlightBookingRequest request) {
        validatorChain.validateAll(request);
    }
}
