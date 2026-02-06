package com.example.travel.phoneservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "phones")
public class PhoneEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phone;
    private String sagaId;
    public PhoneEntity(){}
    // getters/setters
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getPhone(){return phone;} public void setPhone(String p){this.phone=p;}
    public String getSagaId(){return sagaId;} public void setSagaId(String s){this.sagaId=s;}
}
