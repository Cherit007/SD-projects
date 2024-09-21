package org.parkinglot.exception;

public class NoFreeSlotAvailableException extends RuntimeException{
    public NoFreeSlotAvailableException(){
        super("No free slot available.");
    }
}
