package com.example.travel.adapter.validators.phonenumber;

import com.example.travel.adapter.validators.Validator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PhoneNumberValidator implements Validator<String> {

    @Override
    public List<String> validate(String phoneNumber) {
        List<String> errors = new ArrayList<>();

        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            errors.add("Phone number cannot be null or empty.");
        }
        // The regex in PhoneNumberQueryHandler already ensures it's 10 digits.
        // Additional validation (e.g., format, country code) could be added here.

        return errors;
    }
}
