package org.flipmed.commands;

import org.flipmed.services.DoctorService;

import java.util.ArrayList;
import java.util.List;

class MarkDoctorAvailCommand implements Command {
    private DoctorService doctorService;

    public MarkDoctorAvailCommand(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @Override
    public boolean matches(String input) {
        return input.startsWith("markDocAvail:");
    }

    @Override
    public void execute(String input) {
        // Expected format: markDocAvail: DrName 9:30-10:00, 12:30-13:00, 16:00-16:30
        String[] parts = input.split(":", 2);
        if(parts.length < 2) {
            System.out.println("Invalid input format for markDocAvail");
            return;
        }
        String params = parts[1].trim();
        String[] tokens = params.split(" ", 2);
        if(tokens.length < 2) {
            System.out.println("Invalid input format for markDocAvail");
            return;
        }
        String docName = tokens[0].trim();
        String slotsStr = tokens[1].trim();
        String[] slotTokens = slotsStr.split(",");
        List<String> slotsList = new ArrayList<>();
        for(String s : slotTokens) {
            slotsList.add(s.trim());
        }
        doctorService.markAvailability(docName, slotsList);
    }
}