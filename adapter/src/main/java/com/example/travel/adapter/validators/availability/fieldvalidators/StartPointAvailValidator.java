package com.example.travel.adapter.validators.availability.fieldvalidators;

import org.springframework.stereotype.Component;
import com.example.travel.adapter.validators.availability.AvailValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class StartPointAvailValidator implements AvailValidator<Map<String, Object>> {

    @Override
    public List<String> validate(Map<String, Object> availabilityDetails) {
        List<String> errors = new ArrayList<>();
        Object startPointObj = availabilityDetails.get("startPoint");

        if (!(startPointObj instanceof String) || ((String) startPointObj).trim().isEmpty()) {
            errors.add("End point is missing");
        } else {
            String endPoint = ((String) startPointObj).trim();
            if (!endPoint.matches("[a-zA-Z]{3}")) {
                errors.add("Invalid OffBoard point");
            }
        }
        return errors;
    }
}
