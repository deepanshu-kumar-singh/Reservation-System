package com.example.travel.adapter.validators.availability.fieldvalidators;


import com.example.travel.adapter.validators.availability.AvailValidator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class DateAvailValidator implements AvailValidator<Map<String, Object>> {

    @Override
    public List<String> validate(Map<String, Object> availabilityDetails) {
        List<String> errors = new ArrayList<>();
        Object dateObj = availabilityDetails.get("date");

        if (!(dateObj instanceof String) || ((String) dateObj).trim().isEmpty()) {
            errors.add("Date is missing");
        } else {
            String dateStr = ((String) dateObj).trim();
            if (!dateStr.matches("\\d{2}[a-zA-Z]{3}")) {
                errors.add("Invalid Date");
            }
        }
        return errors;
    }
}
