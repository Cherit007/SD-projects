package org.flipmed.repository;

import org.flipmed.entities.Appointment;

import java.util.HashMap;
import java.util.Map;

public class AppointmentRepository {
    private static AppointmentRepository instance;
    private Map<Integer, Appointment> appointmentMap;

    private AppointmentRepository() {
        appointmentMap = new HashMap<>();
    }

    public static AppointmentRepository getInstance() {
        if(instance == null)
            instance = new AppointmentRepository();
        return instance;
    }

    public void addAppointment(Appointment appointment) {
        appointmentMap.put(appointment.getBookingId(), appointment);
    }

    public Appointment getAppointment(int bookingId) {
        return appointmentMap.get(bookingId);
    }

    public void removeAppointment(int bookingId) {
        appointmentMap.remove(bookingId);
    }
}