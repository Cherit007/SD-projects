package org.flipmed.commands;

import org.flipmed.services.PatientService;

class RegisterPatientCommand implements Command {
    private PatientService patientService;

    public RegisterPatientCommand(PatientService patientService) {
        this.patientService = patientService;
    }

    @Override
    public boolean matches(String input) {
        return input.startsWith("registerPatient:");
    }

    @Override
    public void execute(String input) {
        // Expected format: registerPatient: PatientName
        String[] parts = input.split(":", 2);
        if(parts.length < 2) {
            System.out.println("Invalid input format for registerPatient");
            return;
        }
        String patientName = parts[1].trim();
        patientService.registerPatient(patientName);
    }
}