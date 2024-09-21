package org.parkinglot.exception;

public class InvalidSlotException extends RuntimeException{
    public InvalidSlotException(){
        super("Invalid slot number");
    }
}
