package org.parkinglot;

public class OutPutPrinter {
    public void welcome() {
        printWithNewLine("Welcome to Parking lot.\n Please enter command to create parking lot with number of floors,number of slots per floor with space");
    }
    public void end() {
        printWithNewLine("Thanks for using Go-Jek Parking lot service.");
    }
    public void invalidFile() {
        printWithNewLine("Invalid file given.");
    }
    public void printWithNewLine(final String msg) {
        System.out.println(msg);
    }
}
//create_parking_lot 2 5 2 2
//park_vehicle 1 BIKE TS299 red
//unpark_vehicle 2 -