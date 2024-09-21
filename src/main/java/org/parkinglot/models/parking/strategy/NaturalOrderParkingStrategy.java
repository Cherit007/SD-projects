package org.parkinglot.models.parking.strategy;

import org.parkinglot.models.VehicleType;
import org.parkinglot.models.Floors;
import org.parkinglot.models.ParkingLot;
import org.parkinglot.models.Slot;
import org.parkinglot.models.SlotType;

import java.util.Map;

public class NaturalOrderParkingStrategy implements ParkingStrategy{
    public Object[] findSlot(ParkingLot parkingLot, VehicleType vehicleType) {
        for (Floors floor : parkingLot.getFloors()) {
            Map<Integer,Slot> slotList = floor.getSlotList();
            for (Map.Entry<Integer, Slot> entry : slotList.entrySet()) {
                Slot slot = entry.getValue();
                if (!slot.isOccupied() && slot.getSlotType().equals(vehicleTypeToSlotType(vehicleType))) {
                    return new Object[]{slot, floor};
                }
            }
        }
        return null;
    }

    private SlotType vehicleTypeToSlotType(VehicleType vehicleType) {
        switch (vehicleType) {
            case CAR:
                return SlotType.CAR;
            case BIKE:
                return SlotType.BIKE;
            case TRUCK:
                return SlotType.TRUCK;
            default:
                throw new IllegalArgumentException("Unknown vehicle type");
        }
    }
}
