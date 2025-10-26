package com.example.travel.adapter.validators.availability.fieldvalidators;


import com.example.travel.adapter.validators.availability.AvailValidator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.travel.adapter.constant.Constant.DATE;
import static com.example.travel.adapter.constant.ErrorCodes.ERR_003;
import static com.example.travel.adapter.constant.RegexConstant.DATE_REGEX;

@Component
public class DateAvailValidator implements AvailValidator<Map<String, Object>> {

    @Override
    public List<String> validate(Map<String, Object> availabilityDetails) {
        List<String> errors = new ArrayList<>();
        Object dateObj = availabilityDetails.get(DATE);

        if (!(dateObj instanceof String) || ((String) dateObj).trim().isEmpty()) {
            errors.add(ERR_003);
        } else {
            String dateStr = ((String) dateObj).trim();
            if (!dateStr.matches(DATE_REGEX)) {
                errors.add("Invalid Date");
            }
        }
        return errors;
    }
}
