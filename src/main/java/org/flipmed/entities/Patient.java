package org.flipmed.entities;

import java.util.ArrayList;
import java.util.List;

public class Patient {
    private String name;
    private List<Appointment> appointments;

    public Patient(String name) {
        this.name = name;
        this.appointments = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
    }

    public void printAppointments() {
        if(appointments.isEmpty()){
            System.out.println("No appointments booked for " + name);
            return;
        }
        for(Appointment app : appointments) {
            System.out.println(app);
        }
    }
}