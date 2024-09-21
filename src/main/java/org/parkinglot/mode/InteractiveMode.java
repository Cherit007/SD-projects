package org.parkinglot.mode;

import org.parkinglot.OutPutPrinter;
import org.parkinglot.commands.Command;
import org.parkinglot.commands.CommandExecutoryFactory;
import org.parkinglot.commands.CommandKeywords;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InteractiveMode extends Mode{
    public InteractiveMode(
            final CommandExecutoryFactory commandExecutorFactory, final OutPutPrinter outputPrinter) {
        super(commandExecutorFactory, outputPrinter);
    }

    @Override
    public void process() throws IOException {
            outputPrinter.welcome();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
            try {
                final String input = reader.readLine();
                final Command command = new Command(input);
                processCommand(command);
                if (command.getCommand().equals(CommandKeywords.EXIT_COMMAND)) {
                    break;
                }
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
