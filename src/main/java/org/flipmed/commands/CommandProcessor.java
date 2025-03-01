package org.flipmed.commands;

import org.flipmed.repository.DoctorRepository;
import org.flipmed.services.DoctorService;
import org.flipmed.services.PatientService;

import java.util.ArrayList;
import java.util.List;

public class CommandProcessor {
    private List<Command> commands;

    public CommandProcessor() {
        commands = new ArrayList<>();
        // Instantiate services and repositories
        DoctorService doctorService = new DoctorService();
        PatientService patientService = new PatientService();
        DoctorRepository doctorRepo = DoctorRepository.getInstance();

        // Register all available commands
        commands.add(new RegisterDoctorCommand(doctorService));
        commands.add(new MarkDoctorAvailCommand(doctorService));
        commands.add(new ShowAvailBySpecialityCommand(patientService));
        commands.add(new BookAppointmentCommand(patientService));
        commands.add(new CancelBookingCommand(patientService));
        commands.add(new RegisterPatientCommand(patientService));
        commands.add(new ViewAppointmentsCommand(patientService));
        commands.add(new TrendingDoctorCommand(doctorRepo));
    }

    public void process(String input) {
        for(Command command : commands) {
            if(command.matches(input)) {
                command.execute(input);
                return;
            }
        }
        System.out.println("Unknown command");
    }
}