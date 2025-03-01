package org.flipmed.services;

import org.flipmed.entities.*;
import org.flipmed.repository.AppointmentRepository;
import org.flipmed.repository.DoctorRepository;
import org.flipmed.repository.PatientRepository;
import org.flipmed.strategies.DefaultSlotRankingStrategy;
import org.flipmed.strategies.SlotRankingStrategy;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PatientService {
    private PatientRepository patientRepo = PatientRepository.getInstance();
    private DoctorRepository doctorRepo = DoctorRepository.getInstance();
    private AppointmentRepository appointmentRepo = AppointmentRepository.getInstance();
    private SlotRankingStrategy rankingStrategy = new DefaultSlotRankingStrategy();

    public void registerPatient(String name) {
        if(patientRepo.getPatient(name) == null) {
            Patient patient = new Patient(name);
            patientRepo.addPatient(patient);
        }
        System.out.println("Welcome " + name + " !!");
    }

    public void showAvailabilityBySpeciality(String specialityStr) {
        Speciality speciality;
        try {
            speciality = Speciality.fromString(specialityStr);
        } catch(Exception e) {
            System.out.println("Invalid speciality provided.");
            return;
        }
        List<AvailableSlot> availableSlots = new ArrayList<>();
        for(Doctor doc : doctorRepo.getAllDoctors()) {
            if(doc.getSpeciality() == speciality) {
                for(TimeSlot slot : doc.getAvailableSlots()) {
                    availableSlots.add(new AvailableSlot(doc, slot));
                }
            }
        }
        // sort using default ranking (by start time)
        Collections.sort(availableSlots, new Comparator<AvailableSlot>() {
            @Override
            public int compare(AvailableSlot o1, AvailableSlot o2) {
                return rankingStrategy.compare(o1.getDoctor(), o1.getSlot(), o2.getSlot());
            }
        });
        if(availableSlots.isEmpty()){
            System.out.println("No available slots");
        } else {
            for(AvailableSlot as : availableSlots) {
                System.out.println("Dr." + as.getDoctor().getName() + ": (" + as.getSlot() + ")");
            }
        }
    }

    public void bookAppointment(String patientName, String doctorName, String slotTimeStr) {
        // For booking, the patient provides a start time; we assume the slot is [start, start+30min]
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
        LocalTime start;
        try {
            start = LocalTime.parse(slotTimeStr, formatter);
        } catch(Exception e) {
            System.out.println("Invalid time format");
            return;
        }
        LocalTime end = start.plusMinutes(30);
        TimeSlot slot = new TimeSlot(start, end);

        // Get or register patient
        Patient patient = patientRepo.getPatient(patientName);
        if(patient == null) {
            patient = new Patient(patientName);
            patientRepo.addPatient(patient);
        }

        // Check patient does not already have an appointment in the same slot
        for(Appointment app : patient.getAppointments()) {
            if(app.getSlot().equals(slot)) {
                System.out.println("Patient already has an appointment at that time");
                return;
            }
        }

        Doctor doctor = doctorRepo.getDoctor(doctorName);
        if(doctor == null) {
            System.out.println("Doctor not found");
            return;
        }

        // Check if the doctor has this slot available
        if(doctor.getAvailableSlots().contains(slot)) {
            Appointment appointment = new Appointment(doctor, patient, slot);
            doctor.bookAppointment(slot, appointment);
            patient.addAppointment(appointment);
            appointmentRepo.addAppointment(appointment);
            System.out.println("Booked. Booking id: " + appointment.getBookingId());
        } else {
            // If already booked, add patient to waitlist
            doctor.addToWaitlist(slot, patient);
            System.out.println("Slot not available. Added to waitlist.");
        }
    }

    public void cancelAppointment(int bookingId) {
        Appointment appointment = appointmentRepo.getAppointment(bookingId);
        if(appointment == null) {
            System.out.println("Invalid Booking Id");
            return;
        }
        Doctor doctor = appointment.getDoctor();
        Patient patient = appointment.getPatient();
        TimeSlot slot = appointment.getSlot();
        doctor.cancelAppointment(slot);
        patient.removeAppointment(appointment);
        appointmentRepo.removeAppointment(bookingId);
        System.out.println("Booking Cancelled");
        // Check if any patient is waiting for this slot
        if(doctor.hasWaitlist(slot)) {
            Patient waitingPatient = doctor.pollWaitlist(slot);
            Appointment newAppointment = new Appointment(doctor, waitingPatient, slot);
            doctor.bookAppointment(slot, newAppointment);
            waitingPatient.addAppointment(newAppointment);
            appointmentRepo.addAppointment(newAppointment);
            System.out.println("Appointment booked for waiting patient: " + waitingPatient.getName()
                    + " with Booking id: " + newAppointment.getBookingId());
        }
    }

    public void viewAppointments(String userName, boolean isDoctor) {
        if(isDoctor) {
            Doctor doctor = doctorRepo.getDoctor(userName);
            if(doctor == null) {
                System.out.println("Doctor not found");
                return;
            }
            doctor.printAppointments();
        } else {
            Patient patient = patientRepo.getPatient(userName);
            if(patient == null) {
                System.out.println("Patient not found");
                return;
            }
            patient.printAppointments();
        }
    }
}