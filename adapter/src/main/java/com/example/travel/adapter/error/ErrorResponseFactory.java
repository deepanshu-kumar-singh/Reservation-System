package com.example.travel.adapter.error;

import org.springframework.stereotype.Component;

@Component
public class ErrorResponseFactory {

    private final ErrorMessageService errorMessageService;

    public ErrorResponseFactory(ErrorMessageService errorMessageService) {
        this.errorMessageService = errorMessageService;
    }

    public String createErrorResponse(String errorCode) {
        return errorMessageService.getMessage(errorCode);
    }
}
