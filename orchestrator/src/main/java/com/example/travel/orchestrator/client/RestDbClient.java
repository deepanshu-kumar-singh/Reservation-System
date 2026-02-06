package com.example.travel.orchestrator.client;

import org.springframework.web.client.RestTemplate;
import java.util.Map;

public class RestDbClient {
    private final RestTemplate rest = new RestTemplate();
    public void createSaga(String sagaId, String status){
        rest.postForObject("http://db-manager:8082/saga", Map.of("sagaId", sagaId, "status", status), Map.class);
    }
    public void updateSaga(String sagaId, String status){
        rest.postForObject("http://db-manager:8082/saga", Map.of("sagaId", sagaId, "status", status), Map.class);
    }
}
