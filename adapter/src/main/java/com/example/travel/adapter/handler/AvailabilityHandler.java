package com.example.travel.adapter.handler;

import com.example.travel.adapter.service.booking.availability.AvailabilityService;
import com.example.travel.adapter.validators.availability.AvailabilityAvailValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import static com.example.travel.adapter.constant.Constant.*;
import static com.example.travel.adapter.constant.ErrorCodes.INVALID_AVAILABILITY_COMMAND;
import static com.example.travel.adapter.constant.RegexConstant.AVAILABILITY_PATTERN;

public class AvailabilityHandler extends AbstractBookingHandler {

    private final AvailabilityAvailValidator validator;
    private final AvailabilityService availabilityService;

    public AvailabilityHandler(AvailabilityService availabilityService, AvailabilityAvailValidator validator) {
        this.availabilityService = availabilityService;
        this.validator = validator;
    }

    @Override
    public String handle(String request) {
        Matcher matcher = AVAILABILITY_PATTERN.matcher(request);
        if (matcher.matches()) {
            Map<String, Object> availabilityDetails = new HashMap<>();
            availabilityDetails.put(DATE, matcher.group(1));
            availabilityDetails.put(START_POINT, matcher.group(2));
            availabilityDetails.put(END_POINT, matcher.group(3));
            availabilityDetails.put("RequestType", "availability");

            List<String> errors = validator.validate(availabilityDetails);
            if (errors.isEmpty()) {
                return availabilityService.checkAvailability(availabilityDetails);
            }
            return INVALID_AVAILABILITY_COMMAND;
        }
        return handleNext(request);
    }
}
