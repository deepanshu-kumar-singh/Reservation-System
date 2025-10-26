package com.example.travel.adapter.validators.sell;

import java.util.List;

public interface Validator<T> {
    List<String> validate(T input);
}