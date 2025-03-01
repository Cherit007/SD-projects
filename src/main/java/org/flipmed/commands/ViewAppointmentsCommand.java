package org.flipmed.commands;

import org.flipmed.services.PatientService;

class ViewAppointmentsCommand implements Command {
    private PatientService patientService;

    public ViewAppointmentsCommand(PatientService patientService) {
        this.patientService = patientService;
    }

    @Override
    public boolean matches(String input) {
        return input.startsWith("viewAppointments:");
    }

    @Override
    public void execute(String input) {
        // Expected format: viewAppointments: UserName (prefix "Dr." for doctors)
        String[] parts = input.split(":", 2);
        if(parts.length < 2) {
            System.out.println("Invalid input format for viewAppointments");
            return;
        }
        String userName = parts[1].trim();
        if(userName.startsWith("Dr.")) {
            userName = userName.substring(3).trim();
            patientService.viewAppointments(userName, true);
        } else {
            patientService.viewAppointments(userName, false);
        }
    }
}