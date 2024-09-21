package org.parkinglot.models;

public class Entry {
    private String id;
    private DisplayBoard displayBoard;
    private Ticket ticket;
    private int entryNumber;

    public Entry(String id, DisplayBoard displayBoard, Ticket ticket, int entryNumber) {
        this.id = id;
        this.displayBoard = displayBoard;
        this.ticket = ticket;
        this.entryNumber = entryNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public DisplayBoard getDisplayBoard() {
        return displayBoard;
    }

    public void setDisplayBoard(DisplayBoard displayBoard) {
        this.displayBoard = displayBoard;
    }

    public int getEntryNumber() {
        return entryNumber;
    }

    public void setEntryNumber(int entryNumber) {
        this.entryNumber = entryNumber;
    }

    @Override
    public String toString() {
        return "Ticket {" +
                "id='" + id + '\'' +
                "entryNumber='" + entryNumber + '\'' +
                ", displayBoard=" + displayBoard +
                ", ticket='" + ticket + '\'' +
                '}';
    }
}
