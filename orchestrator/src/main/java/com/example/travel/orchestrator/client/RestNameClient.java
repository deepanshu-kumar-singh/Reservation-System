package com.example.travel.orchestrator.client;

import org.springframework.web.client.RestTemplate;
import java.util.Map;

public class RestNameClient {
    private final RestTemplate rest = new RestTemplate();
    public Long create(String first, String last, String sagaId){
        Map resp = rest.postForObject("http://name-service:8083/names", Map.of("firstName", first, "lastName", last, "sagaId", sagaId), Map.class);
        return Long.valueOf(String.valueOf(resp.get("id")));
    }
    public void compensate(String sagaId){
        rest.postForObject("http://name-service:8083/names/compensate/"+sagaId, null, Map.class);
    }
}
