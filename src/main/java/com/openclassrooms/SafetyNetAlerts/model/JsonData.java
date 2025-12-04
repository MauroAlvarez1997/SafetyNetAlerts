package com.openclassrooms.SafetyNetAlerts.model;

import java.util.List;

//Jackson cannot parse a JSON file that has multiple lists at the root unless you provide a wrapper class representing that root.
public class JsonData {

    private List<Person> persons;
//    private List<Firestation> firestations;
//    private List<MedicalRecord> medicalrecords;

    public List<Person> getPersons() {
        return persons;
    }

//    public List<Firestation> getFirestations() {
//        return firestations;
//    }
//
//    public List<MedicalRecord> getMedicalrecords() {
//        return medicalrecords;
//    }
}
