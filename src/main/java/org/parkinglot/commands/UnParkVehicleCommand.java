package org.parkinglot.commands;

import org.parkinglot.OutPutPrinter;
import org.parkinglot.models.Bill;
import org.parkinglot.models.Vehicle;
import org.parkinglot.services.ParkingLotService;

import java.util.List;

public class UnParkVehicleCommand extends CommandExecutor{
    final static String COMMAND_NAME="unpark_vehicle";

    public UnParkVehicleCommand(ParkingLotService parkingLotService, OutPutPrinter outPutPrinter){
        super(parkingLotService,outPutPrinter);
    }
    @Override
    public void execute(Command command) {
        int exitNumber= Integer.parseInt(command.getParams().get(0));
        String registrationNumber = command.getParams().get(1);
        Bill newBill=parkingLotService.generateBillAtExit(exitNumber,registrationNumber);
        outPutPrinter.printWithNewLine("Vehicle is unparked with amount paid as "+ newBill.getAmount());
    }

    @Override
    public boolean validate(Command command) {
        List<String> tokens= command.getParams();
        if(tokens.size()!=2) return false;
        return true;
    }
}
