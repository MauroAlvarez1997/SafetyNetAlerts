package com.openclassrooms.SafetyNetAlerts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Represents the structure of the main JSON data file used in the application.
 * Contains lists of persons, fire stations, and medical records.
 * JSON property names are mapped to Java fields using {@link JsonProperty}.
 */
public class JsonData {

    private List<Person> persons;

    @JsonProperty("firestations")
    private List<FireStation> fireStations;

    @JsonProperty("medicalrecords")
    private List<MedicalRecord> medicalRecords;

    /**
     * Gets the list of persons.
     *
     * @return list of persons
     */
    public List<Person> getPersons() {
        return persons;
    }

    /**
     * Sets the list of persons.
     *
     * @param persons list of persons to set
     */
    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    /**
     * Gets the list of fire stations.
     *
     * @return list of fire stations
     */
    public List<FireStation> getFireStations() {
        return fireStations;
    }

    /**
     * Sets the list of fire stations.
     *
     * @param fireStations list of fire stations to set
     */
    public void setFireStations(List<FireStation> fireStations) {
        this.fireStations = fireStations;
    }

    /**
     * Gets the list of medical records.
     *
     * @return list of medical records
     */
    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    /**
     * Sets the list of medical records.
     *
     * @param medicalRecords list of medical records to set
     */
    public void setMedicalRecords(List<MedicalRecord> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }
}
