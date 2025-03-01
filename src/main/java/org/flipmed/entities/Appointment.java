package org.flipmed.entities;

public class Appointment {
    private static int idCounter = 1;
    private int bookingId;
    private Doctor doctor;
    private Patient patient;
    private TimeSlot slot;

    public Appointment(Doctor doctor, Patient patient, TimeSlot slot) {
        this.doctor = doctor;
        this.patient = patient;
        this.slot = slot;
        this.bookingId = idCounter++;
    }

    public int getBookingId() {
        return bookingId;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public TimeSlot getSlot() {
        return slot;
    }

    @Override
    public String toString() {
        return "Booking id: " + bookingId + " with Dr." + doctor.getName() + " at " + slot;
    }
}