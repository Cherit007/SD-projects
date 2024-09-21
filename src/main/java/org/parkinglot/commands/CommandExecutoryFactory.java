package org.parkinglot.commands;

import org.parkinglot.OutPutPrinter;
import org.parkinglot.exception.InvalidCommandException;
import org.parkinglot.services.ParkingLotService;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutoryFactory {
    private Map<String, CommandExecutor> commands = new HashMap<>();

    public CommandExecutoryFactory(ParkingLotService parkingLotService,OutPutPrinter outPutPrinter){
        commands.put(
                CreateNewParkingLotCommand.COMMAND_NAME,
                new CreateNewParkingLotCommand(parkingLotService,outPutPrinter)
        );
        commands.put(
                ParkVehicleCommand.COMMAND_NAME,
                new ParkVehicleCommand(parkingLotService,outPutPrinter)
        );
        commands.put(
                ParkingLotStatusCommand.COMMAND_NAME,
                new ParkingLotStatusCommand(parkingLotService,outPutPrinter)
        );
        commands.put(
                UnParkVehicleCommand.COMMAND_NAME,
                new UnParkVehicleCommand(parkingLotService,outPutPrinter)
        );
        commands.put(
                DisplayCommand.COMMAND_NAME,
                new DisplayCommand(parkingLotService,outPutPrinter)
        );
    }

    public CommandExecutor getCommandExecutor(final Command command) {
        final CommandExecutor commandExecutor = commands.get(command.getCommand());
        if (commandExecutor == null) {
            throw new InvalidCommandException();
        }
        return commandExecutor;
    }
}
