package org.flipmed.entities;

public class AvailableSlot {
    private Doctor doctor;
    private TimeSlot slot;

    public AvailableSlot(Doctor doctor, TimeSlot slot) {
        this.doctor = doctor;
        this.slot = slot;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public TimeSlot getSlot() {
        return slot;
    }
}