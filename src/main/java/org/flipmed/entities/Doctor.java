package org.flipmed.entities;

import java.util.*;

public class Doctor {
    private String name;
    private Speciality speciality;
    // Available slots that are not yet booked
    private Set<TimeSlot> availableSlots;
    // Mapping from a slot to a booked appointment
    private Map<TimeSlot, Appointment> bookedAppointments;
    // Mapping from a slot to a waitlist of patients
    private Map<TimeSlot, Queue<Patient>> waitlists;
    private int appointmentCount; // used for trending doctor

    public Doctor(String name, Speciality speciality) {
        this.name = name;
        this.speciality = speciality;
        this.availableSlots = new HashSet<>();
        this.bookedAppointments = new HashMap<>();
        this.waitlists = new HashMap<>();
        this.appointmentCount = 0;
    }

    public String getName() {
        return name;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public Set<TimeSlot> getAvailableSlots() {
        return availableSlots;
    }

    public int getAppointmentCount() {
        return appointmentCount;
    }

    public void addAvailability(List<TimeSlot> slots) {
        // Only add slots that are within the valid working hours (9am-9pm) if needed.
        for(TimeSlot slot : slots) {
            // We assume slot times have been validated for 30 mins duration.
            // Here, we add slot if not already booked.
            if(!bookedAppointments.containsKey(slot))
                availableSlots.add(slot);
        }
    }

    public void bookAppointment(TimeSlot slot, Appointment appointment) {
        // Remove the slot from available slots and mark as booked.
        availableSlots.remove(slot);
        bookedAppointments.put(slot, appointment);
        appointmentCount++;
    }

    public void cancelAppointment(TimeSlot slot) {
        bookedAppointments.remove(slot);
        // When cancelling, add the slot back to available slots.
        availableSlots.add(slot);
        appointmentCount = Math.max(0, appointmentCount - 1);
    }

    public void addToWaitlist(TimeSlot slot, Patient patient) {
        waitlists.putIfAbsent(slot, new LinkedList<>());
        waitlists.get(slot).offer(patient);
    }

    public boolean hasWaitlist(TimeSlot slot) {
        return waitlists.containsKey(slot) && !waitlists.get(slot).isEmpty();
    }

    public Patient pollWaitlist(TimeSlot slot) {
        if(hasWaitlist(slot)) {
            return waitlists.get(slot).poll();
        }
        return null;
    }

    public void printAppointments() {
        if(bookedAppointments.isEmpty()){
            System.out.println("No appointments booked for Dr." + name);
            return;
        }
        for(Appointment app : bookedAppointments.values()){
            System.out.println(app);
        }
    }
}