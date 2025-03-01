package org.flipmed.commands;

import org.flipmed.services.PatientService;

class ShowAvailBySpecialityCommand implements Command {
    private PatientService patientService;

    public ShowAvailBySpecialityCommand(PatientService patientService) {
        this.patientService = patientService;
    }

    @Override
    public boolean matches(String input) {
        return input.startsWith("showAvailByspeciality:");
    }

    @Override
    public void execute(String input) {
        // Expected format: showAvailByspeciality: Speciality
        String[] parts = input.split(":", 2);
        if(parts.length < 2) {
            System.out.println("Invalid input format for showAvailByspeciality");
            return;
        }
        String specialityStr = parts[1].trim();
        patientService.showAvailabilityBySpeciality(specialityStr);
    }
}