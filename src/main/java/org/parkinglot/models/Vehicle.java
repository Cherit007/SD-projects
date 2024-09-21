package org.parkinglot.models;

public class Vehicle {
    private String id;
    private String number;
    private VehicleType vehicleType;
    private String color;

    public Vehicle(String number,VehicleType vehicleType,String color){
        this.color=color;
        this.vehicleType=vehicleType;
        this.number=number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Person {" +
                "id='" + id + '\'' +
                "number='" + number + '\'' +
                ", vehicleType=" + vehicleType +
                ", color='" + color + '\'' +
                '}';
    }
}
