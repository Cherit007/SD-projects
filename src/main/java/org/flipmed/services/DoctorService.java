package org.flipmed.services;

import org.flipmed.entities.Doctor;
import org.flipmed.entities.Speciality;
import org.flipmed.entities.TimeSlot;
import org.flipmed.repository.DoctorRepository;

import java.util.ArrayList;
import java.util.List;

public class DoctorService {
    private DoctorRepository doctorRepo = DoctorRepository.getInstance();

    public void registerDoctor(String name, Speciality speciality) {
        if(doctorRepo.getDoctor(name) != null) {
            System.out.println("Doctor already registered");
            return;
        }
        Doctor doctor = new Doctor(name, speciality);
        doctorRepo.addDoctor(doctor);
        System.out.println("Welcome Dr. " + name + " !!");
    }

    public void markAvailability(String doctorName, List<String> slotStrings) {
        Doctor doctor = doctorRepo.getDoctor(doctorName);
        if(doctor == null) {
            System.out.println("Doctor not found");
            return;
        }
        List<TimeSlot> slots = new ArrayList<>();
        for(String slotStr : slotStrings) {
            try {
                TimeSlot slot = TimeSlot.parse(slotStr);
                slots.add(slot);
            } catch(Exception e) {
                System.out.println("Error: " + e.getMessage());
                return;
            }
        }
        doctor.addAvailability(slots);
        System.out.println("Done Doc!");
    }
}