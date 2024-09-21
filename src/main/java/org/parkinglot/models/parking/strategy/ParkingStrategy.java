package org.parkinglot.models.parking.strategy;

import org.parkinglot.models.VehicleType;
import org.parkinglot.models.ParkingLot;

public interface ParkingStrategy {
    Object[] findSlot(ParkingLot parkingLot, VehicleType vehicleType);
}
