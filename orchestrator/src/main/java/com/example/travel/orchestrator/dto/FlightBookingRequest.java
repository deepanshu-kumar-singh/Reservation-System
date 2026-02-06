package com.example.travel.orchestrator.dto;

public class FlightBookingRequest {
    private String firstName;
    private String lastName;
    private String phone;
    private Long flightId;
    private String classOfService;
    private String sagaId;
    private String type;
    private Long nameId;
    private Long phoneId;
    private Long reservationId;

    public FlightBookingRequest() {}
    // getters & setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public Long getFlightId() { return flightId; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }
    public String getClassOfService() { return classOfService; }
    public void setClassOfService(String classOfService) { this.classOfService = classOfService; }
    public String getSagaId() { return sagaId; }
    public void setSagaId(String sagaId) { this.sagaId = sagaId; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Long getNameId() { return nameId; }
    public void setNameId(Long nameId) { this.nameId = nameId; }
    public Long getPhoneId() { return phoneId; }
    public void setPhoneId(Long phoneId) { this.phoneId = phoneId; }
    public Long getReservationId() { return reservationId; }
    public void setReservationId(Long reservationId) { this.reservationId = reservationId; }
}
