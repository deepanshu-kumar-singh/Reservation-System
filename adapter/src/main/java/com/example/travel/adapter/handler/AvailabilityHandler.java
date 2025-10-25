package com.example.travel.adapter.handler;

import com.example.travel.adapter.error.ErrorResponseFactory;
import com.example.travel.adapter.service.booking.availability.AvailabilityService;
import com.example.travel.adapter.validators.availability.AvailabilityValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AvailabilityHandler extends AbstractBookingHandler {

    private static final Pattern AVAILABILITY_PATTERN = Pattern.compile("^START\\s+(\\d{1,2}[A-Z]{3})([A-Z]{3})([A-Z]{3})$", Pattern.CASE_INSENSITIVE);
    private final AvailabilityValidator validator;
    private final AvailabilityService availabilityService;

    public AvailabilityHandler(ErrorResponseFactory errorResponseFactory, AvailabilityService availabilityService, AvailabilityValidator validator) {
        super(errorResponseFactory);
        this.availabilityService = availabilityService;
        this.validator = validator;
    }

    @Override
    public String handle(String request) {
        Matcher matcher = AVAILABILITY_PATTERN.matcher(request);
        if (matcher.matches()) {
            Map<String, Object> availabilityDetails = new HashMap<>();
            availabilityDetails.put("date", matcher.group(1));
            availabilityDetails.put("startPoint", matcher.group(2));
            availabilityDetails.put("endPoint", matcher.group(3));

            List<String> errors = validator.validate(availabilityDetails);
            if (errors.isEmpty()) {
                return availabilityService.checkAvailability(availabilityDetails);
            }
            return errorResponseFactory.createErrorResponse("ERR-002");
        }
        return handleNext(request);
    }
}
