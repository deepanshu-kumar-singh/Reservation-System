package com.example.travel.adapter.service;

import com.example.travel.adapter.dto.FlightBookingRequest;
import com.example.travel.adapter.exception.ValidationException;
import com.example.travel.adapter.strategy.BookingErrorStrategy;
import com.example.travel.adapter.strategy.BookingSuccessStrategy;
import com.example.travel.adapter.strategy.BookingValidationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FlightBookingServiceImpl extends AbstractBookingService {

    private final BookingValidationStrategy validationStrategy;
    private final BookingSuccessStrategy successStrategy;
    private final BookingErrorStrategy errorStrategy;

    public FlightBookingServiceImpl(BookingValidationStrategy validationStrategy, BookingSuccessStrategy successStrategy, BookingErrorStrategy errorStrategy) {
        this.validationStrategy = validationStrategy;
        this.successStrategy = successStrategy;
        this.errorStrategy = errorStrategy;
    }

    @Override
    protected void validate(FlightBookingRequest request) throws ValidationException {
        validationStrategy.validate(request);
    }

    @Override
    protected Map<String, String> onSuccess(FlightBookingRequest request) {
        return successStrategy.handleSuccess(request);
    }

    @Override
    protected Map<String, String> onError(FlightBookingRequest request, ValidationException e) {
        return errorStrategy.handleError(request, e);
    }
}