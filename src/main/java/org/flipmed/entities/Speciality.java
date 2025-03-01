package org.flipmed.entities;

public enum Speciality {
    CARDIOLOGIST, DERMATOLOGIST, ORTHOPEDIC, GENERAL_PHYSICIAN;

    public static Speciality fromString(String s) throws Exception {
        switch(s.trim().toLowerCase()){
            case "cardiologist":
                return CARDIOLOGIST;
            case "dermatologist":
                return DERMATOLOGIST;
            case "orthopedic":
                return ORTHOPEDIC;
            case "general physician":
                return GENERAL_PHYSICIAN;
            default:
                throw new Exception("Invalid speciality: " + s);
        }
    }
}