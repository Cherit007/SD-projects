package org.parkinglot.commands;

import org.parkinglot.OutPutPrinter;
import org.parkinglot.services.ParkingLotService;

public abstract class CommandExecutor {
    protected ParkingLotService parkingLotService;
    protected OutPutPrinter outPutPrinter;
    public CommandExecutor(ParkingLotService parkingLotService, OutPutPrinter outPutPrinter){
        this.parkingLotService=parkingLotService;
        this.outPutPrinter=outPutPrinter;
    }
    public abstract void execute(Command command);
    public abstract boolean validate(Command command);
}
