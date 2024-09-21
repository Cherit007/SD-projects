package org.parkinglot.commands;

import org.parkinglot.OutPutPrinter;
import org.parkinglot.models.VehicleType;
import org.parkinglot.services.ParkingLotService;

import java.util.List;

public class DisplayCommand extends CommandExecutor{
    final static String COMMAND_NAME="display";
    final static String SUB_COMMAND_NAME_1="free_count";
    final static String SUB_COMMAND_NAME_2="occupied_slots";
    final static String SUB_COMMAND_NAME_3="free_slots";

    public DisplayCommand(ParkingLotService parkingLotService, OutPutPrinter outPutPrinter){
        super(parkingLotService,outPutPrinter);
    }
    @Override
    public void execute(Command command) {
        String sub_command = command.getParams().get(0);
        VehicleType vehicleType = VehicleType.valueOf(command.getParams().get(1));
        switch (sub_command){
            case SUB_COMMAND_NAME_1 -> parkingLotService.getFreeCountOfVehicleType(vehicleType);
            case SUB_COMMAND_NAME_2 -> parkingLotService.getOccupiedSlotsOfVehicleType(vehicleType);
            case SUB_COMMAND_NAME_3 -> parkingLotService.getFreeSlotsOfVehicleType(vehicleType);
        }
    }

    @Override
    public boolean validate(Command command) {
        List<String> tokens= command.getParams();
        if(tokens.size()!=2) return false;
        return true;
    }
}
