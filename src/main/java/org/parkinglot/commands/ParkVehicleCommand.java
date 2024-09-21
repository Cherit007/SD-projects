package org.parkinglot.commands;

import org.parkinglot.OutPutPrinter;
import org.parkinglot.models.VehicleType;
import org.parkinglot.models.Vehicle;
import org.parkinglot.services.ParkingLotService;

import java.util.List;

public class ParkVehicleCommand extends CommandExecutor {
    final static String COMMAND_NAME="park_vehicle";

    public ParkVehicleCommand(ParkingLotService parkingLotService, OutPutPrinter outPutPrinter){
        super(parkingLotService,outPutPrinter);
    }
    @Override
    public void execute(Command command) {
        int entryNumber = Integer.parseInt(command.getParams().get(0));
        String vehicleType = command.getParams().get(1);
        String regNumber = command.getParams().get(2);
        String color = command.getParams().get(3);
        VehicleType type = VehicleType.valueOf(vehicleType);
        final Vehicle vehicle=new Vehicle(regNumber,type,color);
        parkingLotService.parkVehicle(entryNumber,vehicle);
        outPutPrinter.printWithNewLine("Parked the vehicle coming from entry "+entryNumber+ " and vehicle type as " + vehicleType + " and reg number as "+regNumber+" with color "+color);
    }

    @Override
    public boolean validate(Command command) {
        List<String> params= command.getParams();
        if(params.size()!=4){
            return false;
        }
        return true;
    }
}
