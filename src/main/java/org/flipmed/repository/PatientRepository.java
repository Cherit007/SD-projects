package org.flipmed.repository;

import org.flipmed.entities.Patient;

import java.util.HashMap;
import java.util.Map;

public class PatientRepository {
    private static PatientRepository instance;
    private Map<String, Patient> patientMap;

    private PatientRepository() {
        patientMap = new HashMap<>();
    }

    public static PatientRepository getInstance() {
        if(instance == null)
            instance = new PatientRepository();
        return instance;
    }

    public void addPatient(Patient patient) {
        patientMap.put(patient.getName(), patient);
    }

    public Patient getPatient(String name) {
        return patientMap.get(name);
    }
}