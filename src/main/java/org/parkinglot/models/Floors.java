package org.parkinglot.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Floors {
    private String id;
    private Map<Integer,Slot> slotList;
    private int floorNumber;

    public Floors(final int floorNumber,Map<Integer,Slot> slotList){
        this.floorNumber=floorNumber;
        this.id=id;
        this.slotList=slotList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<Integer,Slot> getSlotList() {
        return slotList;
    }

    public void setSlotList(Map<Integer,Slot> slotList) {
        this.slotList = slotList;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "slotList=" + slotList +
                ", floorNumber=" + floorNumber +
                '}';
    }
}
