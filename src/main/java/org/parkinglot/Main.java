package org.parkinglot;

import org.parkinglot.commands.CommandExecutoryFactory;
import org.parkinglot.mode.InteractiveMode;
import org.parkinglot.services.ParkingLotService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        final OutPutPrinter outputPrinter = new OutPutPrinter();
        final ParkingLotService parkingLotService = new ParkingLotService();
        final CommandExecutoryFactory commandExecutorFactory =
                new CommandExecutoryFactory(parkingLotService,outputPrinter);
        new InteractiveMode(commandExecutorFactory, outputPrinter).process();
    }
}