package org.flipmed.repository;

import org.flipmed.entities.Doctor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DoctorRepository {
    private static DoctorRepository instance;
    private final Map<String, Doctor> doctorMap;

    private DoctorRepository() {
        doctorMap = new HashMap<>();
    }

    public static DoctorRepository getInstance() {
        if(instance == null)
            instance = new DoctorRepository();
        return instance;
    }

    public void addDoctor(Doctor doctor) {
        doctorMap.put(doctor.getName(), doctor);
    }

    public Doctor getDoctor(String name) {
        return doctorMap.get(name);
    }

    public Collection<Doctor> getAllDoctors() {
        return doctorMap.values();
    }

    public Doctor getTrendingDoctor() {
        Doctor trending = null;
        int max = 0;
        for(Doctor doc : doctorMap.values()){
            if(doc.getAppointmentCount() > max){
                max = doc.getAppointmentCount();
                trending = doc;
            }
        }
        return trending;
    }
}