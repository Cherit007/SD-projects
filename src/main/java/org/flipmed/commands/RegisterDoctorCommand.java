package org.flipmed.commands;

import org.flipmed.entities.Speciality;
import org.flipmed.services.DoctorService;

class RegisterDoctorCommand implements Command {
    private DoctorService doctorService;

    public RegisterDoctorCommand(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @Override
    public boolean matches(String input) {
        return input.startsWith("registerDoc");
    }

    @Override
    public void execute(String input) {
        // Expected format: registerDoc -> DrName-> Speciality
        String[] parts = input.split("->");
        if(parts.length != 3) {
            System.out.println("Invalid input format for registerDoc");
            return;
        }
        String docName = parts[1].trim();
        String specialityStr = parts[2].trim();
        try {
            Speciality speciality = Speciality.fromString(specialityStr);
            doctorService.registerDoctor(docName, speciality);
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}