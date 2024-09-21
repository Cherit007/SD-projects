package org.parkinglot.exception;

public class MaxCapacityException extends RuntimeException{
    public MaxCapacityException(){}
    public MaxCapacityException(String message){
        super(message);
    }
}
