package org.parkinglot.commands;

import org.parkinglot.OutPutPrinter;
import org.parkinglot.services.ParkingLotService;

public class ParkingLotStatusCommand extends CommandExecutor{
    final static String COMMAND_NAME="status";
    public ParkingLotStatusCommand(ParkingLotService parkingLotService, OutPutPrinter outPutPrinter){
        super(parkingLotService,outPutPrinter);
    }
    @Override
    public void execute(Command command) {
        parkingLotService.displayStatus();
    }

    @Override
    public boolean validate(Command command) {
        return true;
    }
}
