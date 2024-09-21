package org.parkinglot.models;

import java.util.Date;

public class Ticket {
    private String id;
    private Vehicle vehicle;
    private Date entryTime;
    private Slot slot;
    private Entry entry;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }
    @Override
    public String toString() {
        return "Ticket {" +
                "id='" + id + '\'' +
                "entry='" + entry + '\'' +
                ", vehicle=" + vehicle +
                ", slot='" + slot + '\'' +
                ", entryTime='" + entryTime + '\'' +
                '}';
    }
}
