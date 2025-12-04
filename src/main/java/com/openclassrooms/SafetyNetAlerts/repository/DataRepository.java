package com.openclassrooms.SafetyNetAlerts.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.SafetyNetAlerts.model.*;

import java.io.File;
import java.util.List;


@Repository
public class DataRepository {

    private List<Person> persons;
    private List<Firestation> firestations;
    private List<MedicalRecord> medicalRecords;

    @PostConstruct
    public void init() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            DataWrapper data = mapper.readValue(new File("src/main/resources/data.json"), DataWrapper.class);

            persons = data.getPersons();
            firestations = data.getFirestations();
            medicalRecords = data.getMedicalrecords();

            System.out.println("JSON loaded successfully!");

        } catch (Exception e) {
            throw new RuntimeException("Error loading JSON", e);
        }
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void addPerson(Person p) {
        persons.add(p);
    }
}
