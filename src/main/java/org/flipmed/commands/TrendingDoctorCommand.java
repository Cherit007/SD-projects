package org.flipmed.commands;

import org.flipmed.entities.Doctor;
import org.flipmed.repository.DoctorRepository;

class TrendingDoctorCommand implements Command {
    private DoctorRepository doctorRepo;

    public TrendingDoctorCommand(DoctorRepository doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    @Override
    public boolean matches(String input) {
        return input.startsWith("trendingDoctor");
    }

    @Override
    public void execute(String input) {
        Doctor trending = doctorRepo.getTrendingDoctor();
        if(trending != null) {
            System.out.println("Trending Doctor: Dr." + trending.getName() +
                    " with " + trending.getAppointmentCount() + " appointments.");
        } else {
            System.out.println("No appointments booked yet");
        }
    }
}