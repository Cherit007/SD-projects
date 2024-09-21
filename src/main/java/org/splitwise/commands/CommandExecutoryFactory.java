package org.splitwise.commands;

import org.splitwise.OutPutPrinter;
import org.splitwise.services.SplitWiseService;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutoryFactory {
    private Map<String, CommandExecutor> commands = new HashMap<>();
    public CommandExecutoryFactory(SplitWiseService splitWiseService, OutPutPrinter outPutPrinter){

    }
}
