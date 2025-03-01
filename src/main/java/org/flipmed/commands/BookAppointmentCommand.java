package org.flipmed.commands;

import org.flipmed.services.PatientService;

class BookAppointmentCommand implements Command {
    private PatientService patientService;

    public BookAppointmentCommand(PatientService patientService) {
        this.patientService = patientService;
    }

    @Override
    public boolean matches(String input) {
        return input.startsWith("bookAppointment:");
    }

    @Override
    public void execute(String input) {
        // Expected format: bookAppointment: (PatientName, DoctorName, StartTime)
        String[] parts = input.split(":", 2);
        if(parts.length < 2) {
            System.out.println("Invalid input format for bookAppointment");
            return;
        }
        String params = parts[1].trim();
        if(params.startsWith("(") && params.endsWith(")")) {
            params = params.substring(1, params.length() - 1);
        }
        String[] tokens = params.split(",");
        if(tokens.length != 3) {
            System.out.println("Invalid input format for bookAppointment parameters");
            return;
        }
        String patientName = tokens[0].trim();
        String doctorName = tokens[1].trim();
        String slotTime = tokens[2].trim();
        patientService.bookAppointment(patientName, doctorName, slotTime);
    }
}
