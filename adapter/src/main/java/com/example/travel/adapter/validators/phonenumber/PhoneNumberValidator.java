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
            errors.add("Phone number cannot be null");
        }

        return errors;
    }
}
