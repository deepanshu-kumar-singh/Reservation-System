package com.example.travel.adapter.validators.sell.fieldvalidators;

import org.springframework.stereotype.Component;
import com.example.travel.adapter.validators.sell.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ServiceClassValidator implements Validator<Map<String, Object>> {

    @Override
    public List<String> validate(Map<String, Object> flightDetails) {
        List<String> errors = new ArrayList<>();
        Object serviceClassObj = flightDetails.get("serviceClass");

        if (!(serviceClassObj instanceof String) || ((String) serviceClassObj).trim().isEmpty()) {
            errors.add("Service class is missing");
        } else {
            String serviceClass = ((String) serviceClassObj).trim().toUpperCase();
            if (!serviceClass.matches("^[YFC]$")) {
                errors.add("invalid class of service");
            }
        }
        return errors;
    }
}
