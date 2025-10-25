package com.example.travel.adapter.error;

import org.springframework.stereotype.Service;

@Service
public class ErrorMessageService {

    private final ErrorProperties errorProperties;

    public ErrorMessageService(ErrorProperties errorProperties) {
        this.errorProperties = errorProperties;
    }

    public String getMessage(String errorCode) {
        return errorProperties.getMessages().getOrDefault(errorCode, "An unknown error occurred.");
    }
}
