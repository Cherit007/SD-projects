package org.parkinglot.services;

import org.parkinglot.exception.ParkingLotException;
import org.parkinglot.models.*;
import org.parkinglot.models.parking.strategy.ParkingStrategy;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class ParkingLotService {
    private ParkingLot parkingLot;
    private ParkingStrategy parkingStrategy;

    public void createParkingLot(final ParkingLot parkingLot, ParkingStrategy parkingStrategy){
        if(this.parkingLot!=null){
            throw new ParkingLotException("Parking lot already exists.");
        }
        this.parkingLot=parkingLot;
        this.parkingStrategy=parkingStrategy;
        this.parkingLot.setTickets(new HashMap<>());
        List<Entry> entryArrays = new ArrayList<>();
        for (int i = 0; i < parkingLot.getNoOfEntries(); i++) {
            Entry entry = new Entry(String.valueOf(i),null,null,i );
            entryArrays.add(entry);
        }
        List<Exit> exitArrays = new ArrayList<>();
        for (int i = 0; i < parkingLot.getNoOfExits(); i++) {
            Exit exits = new Exit(String.valueOf(i),i,null );
            exitArrays.add(exits);
        }
        List<Floors> floorArrays = new ArrayList<>();


        for (int i = 0; i < parkingLot.getNoOfFloors(); i++) {
            Map<Integer,Slot> slotsArrays = new HashMap<>();
            SlotType [] slotTypes =SlotType.values();
            for(int j=0;j<parkingLot.getCapacity();j++){
                Slot slot=new Slot(slotTypes[new Random().nextInt(slotTypes.length)],j,false);
                slotsArrays.put(j,slot);
            }
            Floors floors = new Floors(i,slotsArrays);
            floorArrays.add(floors);
        }
        parkingLot.setId(UUID.randomUUID().toString());
        parkingLot.setName("Parkinglot");
        parkingLot.setEntries(entryArrays);
        parkingLot.setExits(exitArrays);
        parkingLot.setFloors(floorArrays);
        System.out.println(parkingLot);
    }

    public void parkVehicle(int entryNumber,Vehicle vehicle){
        validateParkingLotExists();
        Object[] result = parkingStrategy.findSlot(this.parkingLot,vehicle.getVehicleType());
        Slot nextFreeSlot=(Slot)result[0];
        Floors currentFloor = (Floors) result[1];
        Entry entry = this.parkingLot.getEntries().get(entryNumber);
        parkingLot.park(entry,currentFloor,vehicle,nextFreeSlot.getSlotNumber());
    }

    private void validateParkingLotExists() {
        if (parkingLot == null) {
            throw new ParkingLotException("Parking lot does not exists to park.");
        }
    }

    public void displayStatus(){
        List<Slot> slots= parkingLot.displayParkingStatus(this.parkingLot.getFloors());
        if(slots.size()==0) {
            System.out.println("No vehicles parked at the moment.");
        }
        for(Slot slot:slots){
            System.out.println("slotNumber: "+ slot.getSlotNumber() + "| Type: " + slot.getVehicle().getVehicleType() + "| RegNumber: " + slot.getVehicle().getNumber()+ "| Color: " + slot.getVehicle().getColor());
        }
    }

    public void getFreeCountOfVehicleType(VehicleType vehicleType){
        List<Floors> floors = this.parkingLot.getFloors();
        int index=1;
        for(Floors floor : floors) {
            int floorCount=0;
            Map<Integer, Slot> slotMap = floor.getSlotList();
            for (Map.Entry<Integer, Slot> slot : slotMap.entrySet()) {
                Slot slotVal = slot.getValue();
                if (!slotVal.isOccupied() && slotVal.getSlotType().toString().equals(vehicleType.toString())) {
                    floorCount++;
                }
            }
            System.out.println("No. of free slots for "+ vehicleType.toString()+ " on Floor "+ index+": "+floorCount);
            index++;
        }
    }

    public void getOccupiedSlotsOfVehicleType(VehicleType vehicleType){
        List<Floors> floors = this.parkingLot.getFloors();
        int index=1;
        for(Floors floor : floors) {
            List<String> occupiedSlots = new ArrayList<>();
            Map<Integer, Slot> slotMap = floor.getSlotList();
            for (Map.Entry<Integer, Slot> slot : slotMap.entrySet()) {
                Slot slotVal = slot.getValue();
                if (slotVal.isOccupied() && slotVal.getSlotType().toString().equals(vehicleType.toString())) {
                    occupiedSlots.add(String.valueOf(slotVal.getSlotNumber()));
                }
            }
            System.out.println("Occupied slots for "+ vehicleType.toString()+ " on Floor "+ index+": "+ String.join(", ",occupiedSlots));
            index++;
        }
    }

    public void getFreeSlotsOfVehicleType(VehicleType vehicleType){
        List<Floors> floors = this.parkingLot.getFloors();
        int index=1;
        for(Floors floor : floors) {
            List<String> occupiedSlots = new ArrayList<>();
            Map<Integer, Slot> slotMap = floor.getSlotList();
            for (Map.Entry<Integer, Slot> slot : slotMap.entrySet()) {
                Slot slotVal = slot.getValue();
                if (!slotVal.isOccupied() && slotVal.getSlotType().toString().equals(vehicleType.toString())) {
                    occupiedSlots.add(String.valueOf(slotVal.getSlotNumber()));
                }
            }
            System.out.println("Free slots for "+ vehicleType.toString()+ " on Floor "+ index+": "+ String.join(", ",occupiedSlots));
            index++;
        }
    }

    public void freeOccupiedSlot(Ticket vehicleTicket){
        for(Floors floor:this.parkingLot.getFloors()){
            Map<Integer,Slot> slot = floor.getSlotList();
            for(Map.Entry<Integer,Slot> entry : slot.entrySet()){
                Slot slotVal = entry.getValue();
                if(slotVal.isOccupied() && slotVal.getVehicle().getNumber().equals(vehicleTicket.getVehicle().getNumber())){
                    slotVal.setOccupied(false);
                    slotVal.setVehicle(null);
                }
            }
        }
    }

    public Bill generateBillAtExit(int exitNumber,String registrationNumber){
        Map<String,Ticket> parkedTickets=this.parkingLot.getParkedTickets();
        Ticket vehicleTicket=parkedTickets.get(registrationNumber);
        freeOccupiedSlot(vehicleTicket);
        Bill newBill=new Bill();
        newBill.setId(UUID.randomUUID().toString());
        Date exitTime =new Date();
        long diffInMillies = Math.abs(vehicleTicket.getEntryTime().getTime() - exitTime.getTime());
        long diffInHours = (long) Math.ceil(TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS));
        double amountToBePaid= 20 * (diffInHours+1);
        newBill.setExitTime(exitTime);
        newBill.setAmount(amountToBePaid);
        newBill.setTicket(vehicleTicket);
        Exit exit = this.parkingLot.getExits().get(exitNumber);
        exit.setBill(newBill);
        exit.setExitNumber(exitNumber);
        exit.setId(UUID.randomUUID().toString());
        return newBill;
    }
}
