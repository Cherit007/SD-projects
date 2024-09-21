package org.parkinglot.models;

public class Exit {
    private String id;
    private int exitNumber;
    private Bill bill;

    public Exit(String id, int exitNumber, Bill bill) {
        this.id = id;
        this.exitNumber = exitNumber;
        this.bill = bill;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public int getExitNumber() {
        return exitNumber;
    }

    public void setExitNumber(int exitNumber) {
        this.exitNumber = exitNumber;
    }
}
