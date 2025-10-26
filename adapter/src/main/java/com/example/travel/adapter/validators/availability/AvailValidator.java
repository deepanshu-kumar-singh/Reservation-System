package com.example.travel.adapter.validators.availability;

import java.util.List;

public interface AvailValidator<T> {
    List<String> validate(T input);
}