package org.flipmed.commands;

import org.flipmed.services.PatientService;

class CancelBookingCommand implements Command {
    private PatientService patientService;

    public CancelBookingCommand(PatientService patientService) {
        this.patientService = patientService;
    }

    @Override
    public boolean matches(String input) {
        return input.startsWith("cancelBookingId:");
    }

    @Override
    public void execute(String input) {
        // Expected format: cancelBookingId: bookingId
        String[] parts = input.split(":", 2);
        if(parts.length < 2) {
            System.out.println("Invalid input format for cancelBookingId");
            return;
        }
        try {
            int bookingId = Integer.parseInt(parts[1].trim());
            patientService.cancelAppointment(bookingId);
        } catch(NumberFormatException e) {
            System.out.println("Invalid booking id");
        }
    }
}