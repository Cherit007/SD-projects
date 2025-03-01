package org.flipmed;

import org.flipmed.commands.CommandProcessor;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CommandProcessor commandProcessor = new CommandProcessor();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to FlipMed! Enter commands (type 'exit' to quit):");
        while(true) {
            System.out.print(">> ");
            String input = sc.nextLine().trim();
            if(input.equalsIgnoreCase("exit"))
                break;
            commandProcessor.process(input);
        }
        sc.close();
        System.out.println("Exiting FlipMed. Goodbye!");
    }
}