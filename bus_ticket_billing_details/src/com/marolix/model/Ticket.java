package com.marolix.model;

public class Ticket {
    private int id;
    private String passengerName;
    private String source;
    private String pickupPoint;
    private String destination;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPickupPoint() {
        return pickupPoint;
    }

    public void setPickupPoint(String pickupPoint) {
        this.pickupPoint = pickupPoint;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Ticket [id=" + id + ", passengerName=" + passengerName + ", source=" + source + ", pickupPoint="
                + pickupPoint + ", destination=" + destination + ", price=" + price + "]";
    }

    public Ticket(int id, String passengerName, String source, String pickupPoint, String destination, double price) {
        this.id = id;
        this.passengerName = passengerName;
        this.source = source;
        this.pickupPoint = pickupPoint;
        this.destination = destination;
        this.price = price;
    }

    public Ticket() {
    }
}
