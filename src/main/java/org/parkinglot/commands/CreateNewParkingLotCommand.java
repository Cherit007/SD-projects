package org.parkinglot.commands;

import org.parkinglot.OutPutPrinter;
import org.parkinglot.models.ParkingLot;
import org.parkinglot.models.parking.strategy.NaturalOrderParkingStrategy;
import org.parkinglot.services.ParkingLotService;
import org.parkinglot.validator.IntegerValidator;

import java.util.List;

public class CreateNewParkingLotCommand extends CommandExecutor{
    public static String COMMAND_NAME = "create_parking_lot";

    public CreateNewParkingLotCommand(ParkingLotService parkingLotService, OutPutPrinter outPutPrinter){
        super(parkingLotService,outPutPrinter);
    }

    @Override
    public void execute(Command command) {
        int floors = Integer.parseInt(command.getParams().get(0));
        int capacity = Integer.parseInt(command.getParams().get(1));
        int noOfEntries = Integer.parseInt(command.getParams().get(2));
        int noOfExits = Integer.parseInt(command.getParams().get(3));
        final ParkingLot parkingLot=new ParkingLot(floors,noOfEntries,noOfExits,capacity);
        parkingLotService.createParkingLot(parkingLot,new NaturalOrderParkingStrategy());
        outPutPrinter.printWithNewLine("Created a parking lot with "+floors+ " floors and " + parkingLot.getCapacity() + " slots in each floor and "+noOfEntries+" entries and "+noOfExits+"exits");
    }

    @Override
    public boolean validate(Command command) {
        List<String> params= command.getParams();
        if(params.size()!=4){
            return false;
        }
        return IntegerValidator.isInteger(params.get(0)) && IntegerValidator.isInteger(params.get(1)) && IntegerValidator.isInteger(params.get(0)) && IntegerValidator.isInteger(params.get(1)) && IntegerValidator.isInteger(params.get(2)) && IntegerValidator.isInteger(params.get(3));
    }
}
