package org.parkinglot.exception;

public class InvalidCommandException extends RuntimeException {
    public InvalidCommandException(){
        super("Invalid command");
    }
}
