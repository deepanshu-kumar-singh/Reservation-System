package com.example.travel.adapter.router;

import com.example.travel.adapter.handler.BookingFlowHandler;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RegexBasedBookingRouter {

    private final BookingFlowHandler initialHandler;

    public RegexBasedBookingRouter(HandlerChainFactory handlerChainFactory) {
        this.initialHandler = handlerChainFactory.createChain();
    }

    public String route(String request) {
        return initialHandler.handle(request);
    }
}
