package com.example.travel.orchestrator.client;

import org.springframework.web.client.RestTemplate;
import java.util.Map;

public class RestPhoneClient {
    private final RestTemplate rest = new RestTemplate();
    public Long create(String phone, String sagaId){
        Map resp = rest.postForObject("http://phone-service:8084/phones", Map.of("phone", phone, "sagaId", sagaId), Map.class);
        return Long.valueOf(String.valueOf(resp.get("id")));
    }
    public void compensate(String sagaId){
        rest.postForObject("http://phone-service:8084/phones/compensate/"+sagaId, null, Map.class);
    }
}
