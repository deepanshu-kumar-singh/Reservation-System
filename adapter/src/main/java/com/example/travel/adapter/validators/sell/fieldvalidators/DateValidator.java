package com.example.travel.adapter.validators.sell.fieldvalidators;

import org.springframework.stereotype.Component;
import com.example.travel.adapter.validators.sell.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class DateValidator implements Validator<Map<String, Object>> {

    @Override
    public List<String> validate(Map<String, Object> flightDetails) {
        List<String> errors = new ArrayList<>();
        Object dateObj = flightDetails.get("date");

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
