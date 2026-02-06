package com.example.travel.orchestrator.client;

import org.springframework.web.client.RestTemplate;
import java.util.Map;

public class RestSellClient {
    private final RestTemplate rest = new RestTemplate();
    public Long createSell(String sagaId, Long nameId, Long phoneId, Long reservationId){
        Map resp = rest.postForObject("http://flight-sell-service:8087/sells", Map.of("sagaId", sagaId, "nameId", nameId, "phoneId", phoneId, "reservationId", reservationId), Map.class);
        return Long.valueOf(String.valueOf(resp.get("sellId")));
    }
    public void compensate(String sagaId){
        rest.postForObject("http://flight-sell-service:8087/sells/compensate/"+sagaId, null, Map.class);
    }
}
