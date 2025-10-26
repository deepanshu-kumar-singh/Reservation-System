package com.example.travel.adapter.router;

import com.example.travel.adapter.error.ErrorMessageService;
import com.example.travel.adapter.handler.BookingHandler;
import org.springframework.stereotype.Component;

@Component
public class Router {

    private final BookingHandler initialHandler;
    private final ErrorMessageService errorMessageService;


    public Router(HandlerFactory handlerFactory, ErrorMessageService errorMessageService) {
        this.initialHandler = handlerFactory.linkableChain();
        this.errorMessageService = errorMessageService;
    }

    public String route(String request) {
        String handlerResponse = initialHandler.handle(request);

        if (handlerResponse != null && handlerResponse.startsWith("ERR_")) {
            return errorMessageService.getMessage(handlerResponse);
        }
        return handlerResponse;
    }
}
