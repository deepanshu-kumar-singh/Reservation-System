package com.example.travel.adapter.validators.sell.fieldvalidators;

import org.springframework.stereotype.Component;
import com.example.travel.adapter.validators.sell.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class SeatsValidator implements Validator<Map<String, Object>> {

    @Override
    public List<String> validate(Map<String, Object> flightDetails) {
        List<String> errors = new ArrayList<>();
        Object seatsObj = flightDetails.get("seats");

//        if (!(seatsObj instanceof String) || ((String) seatsObj).trim().isEmpty()) {
//            errors.add("Seats information is missing");
//        }
//        else {
//            String seatsStr = ((String) seatsObj).trim().toUpperCase();
//            if (!seatsStr.matches("^[1-9]\\d*$")) {
//                errors.add("invalid seats");
//            }
       // }
        return errors;
    }
}
