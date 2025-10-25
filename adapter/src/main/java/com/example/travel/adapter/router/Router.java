package com.example.travel.adapter.router;

import com.example.travel.adapter.handler.BookingHandler;
import org.springframework.stereotype.Component;

@Component
public class Router {

    private final BookingHandler initialHandler;

    public Router(HandlerFactory handlerFactory) {
        this.initialHandler = handlerFactory.linkableChain();
    }

    public String route(String request) {
        return initialHandler.handle(request);
    }
}
