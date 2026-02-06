package com.example.travel.dbmanager.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "saga_status")
public class SagaStatus {
    @Id
    private String sagaId;
    private String sagaType;
    private String status;
    private String lastStep;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    public SagaStatus() {}
    public SagaStatus(String sagaId, String status){ this.sagaId = sagaId; this.status = status; }
    // getters and setters ...
    public String getSagaId(){return sagaId;} public void setSagaId(String s){this.sagaId=s;}
    public String getStatus(){return status;} public void setStatus(String s){this.status=s;}
}
