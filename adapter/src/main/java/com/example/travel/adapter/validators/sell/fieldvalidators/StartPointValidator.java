package com.example.travel.adapter.validators.sell.fieldvalidators;

import org.springframework.stereotype.Component;
import com.example.travel.adapter.validators.sell.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class StartPointValidator implements Validator<Map<String, Object>> {

    @Override
    public List<String> validate(Map<String, Object> flightDetails) {
        List<String> errors = new ArrayList<>();
        Object startPointObj = flightDetails.get("startPoint");

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
