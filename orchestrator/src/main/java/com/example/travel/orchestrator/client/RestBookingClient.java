package com.example.travel.orchestrator.client;

import org.springframework.web.client.RestTemplate;
import java.util.Map;

public class RestBookingClient {
    private final RestTemplate rest = new RestTemplate();
    public Long createBooking(String sagaId, Long nameId, Long phoneId, Long reservationId){
        Map resp = rest.postForObject("http://flight-booking-service:8086/bookings", Map.of("sagaId", sagaId, "nameId", nameId, "phoneId", phoneId, "reservationId", reservationId), Map.class);
        assert resp != null;
        return Long.valueOf(String.valueOf(resp.get("bookingId")));
    }
    public void compensate(String sagaId){
        rest.postForObject("http://flight-booking-service:8086/bookings/compensate/"+sagaId, null, Map.class);
    }
}
