package org.parkinglot.mode;

import org.parkinglot.OutPutPrinter;
import org.parkinglot.commands.Command;
import org.parkinglot.commands.CommandExecutor;
import org.parkinglot.commands.CommandExecutoryFactory;
import org.parkinglot.exception.InvalidCommandException;

import java.io.IOException;

public abstract class Mode {
    private CommandExecutoryFactory commandExecutorFactory;
    protected OutPutPrinter outputPrinter;

    public Mode(
            final CommandExecutoryFactory commandExecutorFactory, final OutPutPrinter outputPrinter) {
        this.commandExecutorFactory = commandExecutorFactory;
        this.outputPrinter = outputPrinter;
    }

    protected void processCommand(final Command command) {
        final CommandExecutor commandExecutor = commandExecutorFactory.getCommandExecutor(command);
        if (commandExecutor.validate(command)) {
            commandExecutor.execute(command);
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Abstract method to process the mode. Each mode will process in its own way.
     *
     * @throws IOException
     */
    public abstract void process() throws IOException;
}
