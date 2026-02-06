package com.example.travel.nameservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "names")
public class NameEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String sagaId;
    public NameEntity(){}
    // getters and setters
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getFirstName(){return firstName;} public void setFirstName(String f){this.firstName=f;}
    public String getLastName(){return lastName;} public void setLastName(String l){this.lastName=l;}
    public String getSagaId(){return sagaId;} public void setSagaId(String s){this.sagaId=s;}
}
