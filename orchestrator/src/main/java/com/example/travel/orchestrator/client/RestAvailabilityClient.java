package com.example.travel.orchestrator.client;

import org.springframework.web.client.RestTemplate;
import java.util.Map;

public class RestAvailabilityClient {
    private final RestTemplate rest = new RestTemplate();
    public Long reserve(Long flightId, String cls, String sagaId){
        Map resp = rest.postForObject("http://availability-service:8085/availability/reserve", Map.of("flightId", flightId, "classOfService", cls, "sagaId", sagaId), Map.class);
        return Long.valueOf(String.valueOf(resp.get("reservationId")));
    }
    public void release(String sagaId){
        rest.postForObject("http://availability-service:8085/availability/release", Map.of("sagaId", sagaId), Map.class);
    }
}
