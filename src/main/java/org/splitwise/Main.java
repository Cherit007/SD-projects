package org.splitwise;

import org.splitwise.commands.CommandExecutoryFactory;
import org.splitwise.services.SplitWiseService;

public class Main {
    public static void main(String[] args) {
        final OutPutPrinter outPutPrinter = new OutPutPrinter();
        final SplitWiseService splitWiseService = new SplitWiseService();
        final CommandExecutoryFactory commandExecutoryFactory = new CommandExecutoryFactory(
                splitWiseService, outPutPrinter
        );
    }
}