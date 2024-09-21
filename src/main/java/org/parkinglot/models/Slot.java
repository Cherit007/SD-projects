package org.parkinglot.models;

public class Slot {
    private String id;
    private SlotType slotType;
    private int slotNumber;
    private boolean occupied;
    private Vehicle vehicle;

    public Slot(SlotType slotType, int slotNumber,boolean occupied) {
        this.slotType = slotType;
        this.slotNumber = slotNumber;
        setOccupied(occupied);
    }

    public void assignVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SlotType getSlotType() {
        return slotType;
    }

    public void setSlotType(SlotType slotType) {
        this.slotType = slotType;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }
    @Override
    public String toString() {
        return "Slot{" +
                "slotType=" + slotType +
                ", slotNumber=" + slotNumber +
                ", occupied=" + occupied +
                ", vehicle=" + vehicle +
                '}';
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
