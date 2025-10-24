package com.example.travel.adapter.validators.flightbooking;

import com.example.travel.adapter.validators.Validator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class FlightBookingValidator implements Validator<Map<String, Object>> {

    @Override
    public List<String> validate(Map<String, Object> flightDetails) {
        List<String> errors = new ArrayList<>();

        if (flightDetails == null) {
            errors.add("Flight details cannot be null.");
            return errors;
        }

        validateField(flightDetails, "airlineCode", "Airline code is missing or empty.", errors);
        validateField(flightDetails, "serviceClass", "Service class is missing or empty.", errors);
        validateField(flightDetails, "date", "Date is missing or empty.", errors);
        validateField(flightDetails, "startPoint", "Start point is missing or empty.", errors);
        validateField(flightDetails, "endPoint", "End point is missing or empty.", errors);

        if (!flightDetails.containsKey("seats") || !(flightDetails.get("seats") instanceof Integer) || (Integer) flightDetails.get("seats") <= 0) {
            errors.add("Seats must be a positive integer.");
        }

        return errors;
    }

    private void validateField(Map<String, Object> details, String fieldName, String errorMessage, List<String> errors) {
        if (!details.containsKey(fieldName) || details.get(fieldName) == null || ((String) details.get(fieldName)).trim().isEmpty()) {
            errors.add(errorMessage);
        }
    }
}
