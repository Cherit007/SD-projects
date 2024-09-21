package org.parkinglot.models;

import org.parkinglot.exception.InvalidSlotException;
import org.parkinglot.exception.MaxCapacityException;
import org.parkinglot.exception.SlotAlreadyOccupiedException;

import java.util.*;

public class ParkingLot {
    private static final int MAX_CAPACITY=20;
    private String id;
    private List<Floors> floors;
    private List<Entry> entries;
    private List<Exit> exits;
    private String name;
    private int capacity;
    private int noOfFloors;
    private int noOfEntries;
    private int noOfExits;
    private Map<String,Ticket> tickets;

    public ParkingLot(final int floors,final int noOfEntries,final int noOfExits,final int capacity){
        if(capacity>MAX_CAPACITY || capacity<=0){
            throw new MaxCapacityException("Capacity cannot be invalid");
        }
        this.capacity=capacity;
        this.noOfFloors=floors;
        this.noOfEntries=noOfEntries;
        this.noOfExits=noOfExits;
    }

    public List<Exit> getExits() {
        return exits;
    }

    public void setExits(List<Exit> exits) {
        this.exits = exits;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Floors> getFloors() {
        return floors;
    }

    public void setFloors(List<Floors> floors) {
        this.floors = floors;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person {" +
                "id='" + id + '\'' +
                "Name='" + name + '\'' +
                ", floors=" + floors +
                ", entries='" + entries + '\'' +
                ", exits='" + exits + '\'' +
                '}';
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getNoOfFloors() {
        return noOfFloors;
    }

    public void setNoOfFloors(int noOfFloors) {
        this.noOfFloors = noOfFloors;
    }

    public int getNoOfEntries() {
        return noOfEntries;
    }

    public void setNoOfEntries(int noOfEntries) {
        this.noOfEntries = noOfEntries;
    }

    public int getNoOfExits() {
        return noOfExits;
    }

    public void setNoOfExits(int noOfExits) {
        this.noOfExits = noOfExits;
    }

    private Slot getSlot(final Floors floor, final Integer slotNumber,final Vehicle vehicle) {
        if (slotNumber > getCapacity() || slotNumber < 0) {
            throw new InvalidSlotException();
        }
        final Map<Integer, Slot> allSlots = floor.getSlotList();
        if (allSlots.containsKey(slotNumber) && !allSlots.get(slotNumber).isOccupied()) {
            SlotType slotType = SlotType.valueOf(vehicle.getVehicleType().toString());
            allSlots.put(slotNumber, new Slot(slotType,slotNumber,true));
        }
        System.out.println(allSlots.get(slotNumber));
        return allSlots.get(slotNumber);
    }


    public Slot park(Entry entry,final Floors floor, final Vehicle vehicle, final Integer slotNumber) {
        final Slot slot = getSlot(floor,slotNumber,vehicle);
        if (!slot.isOccupied()) {
            throw new SlotAlreadyOccupiedException();
        }
        slot.assignVehicle(vehicle);
        Ticket ticket=generateTicket(vehicle,slot,entry);
        tickets.put(vehicle.getNumber(),ticket);
        return slot;
    }

    public Ticket generateTicket(Vehicle vehicle,Slot slot,Entry entry){
        Ticket ticket = new Ticket();
        ticket.setId(UUID.randomUUID().toString());
        ticket.setEntryTime(new Date());
        ticket.setEntry(entry);
        ticket.setSlot(slot);
        ticket.setVehicle(vehicle);
        return ticket;
    }

    public List<Slot> displayParkingStatus(List<Floors> floors){
        List<Slot> parkedVehicles = new ArrayList<>();
        for (Floors floor : floors){
            Map<Integer,Slot> slotList = floor.getSlotList();
            for (Map.Entry<Integer, Slot> entry : slotList.entrySet()) {
                Slot slot = entry.getValue();
                if(slot.isOccupied()){
                    parkedVehicles.add(slot);
                }
            }
        }
        return parkedVehicles;
    }

    public void setTickets(Map<String,Ticket> tickets) {
        this.tickets = tickets;
    }
    public Map<String,Ticket> getParkedTickets() {
        return this.tickets;
    }
}
