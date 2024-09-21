package org.parkinglot.exception;

public class SlotAlreadyOccupiedException extends RuntimeException{
    public SlotAlreadyOccupiedException(){
        super("Slot already occupied.");
    }
}
