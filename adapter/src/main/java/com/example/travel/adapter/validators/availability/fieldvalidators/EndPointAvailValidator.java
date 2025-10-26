package com.example.travel.adapter.validators.availability.fieldvalidators;

import org.springframework.stereotype.Component;
import com.example.travel.adapter.validators.availability.AvailValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class EndPointAvailValidator implements AvailValidator<Map<String, Object>> {

    @Override
    public List<String> validate(Map<String, Object> availabilityDetails) {
        List<String> errors = new ArrayList<>();
        Object endPointObj = availabilityDetails.get("endPoint");

        if (!(endPointObj instanceof String) || ((String) endPointObj).trim().isEmpty()) {
            errors.add("End point is missing");
        } else {
            String endPoint = ((String) endPointObj).trim();
            if (!endPoint.matches("[a-zA-Z]{3}")) {
                errors.add("Invalid OffBoard point");
            }
        }
        return errors;
    }
}
