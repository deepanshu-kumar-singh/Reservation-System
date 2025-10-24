package com.example.travel.adapter.validators.availability;

import com.example.travel.adapter.validators.Validator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class AvailabilityValidator implements Validator<Map<String, Object>> {

    @Override
    public List<String> validate(Map<String, Object> availabilityDetails) {
        List<String> errors = new ArrayList<>();

        if (availabilityDetails == null) {
            errors.add("Availability details cannot be null.");
            return errors;
        }

        validateField(availabilityDetails, "date", "Date is missing or empty.", errors);
        validateField(availabilityDetails, "startPoint", "Start point is missing or empty.", errors);
        validateField(availabilityDetails, "endPoint", "End point is missing or empty.", errors);

        return errors;
    }

    private void validateField(Map<String, Object> details, String fieldName, String errorMessage, List<String> errors) {
        if (!details.containsKey(fieldName) || details.get(fieldName) == null || ((String) details.get(fieldName)).trim().isEmpty()) {
            errors.add(errorMessage);
        }
    }
}
