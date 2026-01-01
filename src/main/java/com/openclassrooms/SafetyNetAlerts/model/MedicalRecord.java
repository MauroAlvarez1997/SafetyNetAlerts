package com.openclassrooms.SafetyNetAlerts.model;

import java.util.List;

/**
 * Represents a medical record for a person, including birthdate,
 * medications, and allergies.
 */
public class MedicalRecord {
    private String firstName;
    private String lastName;
    private String birthdate;
    private List<String> medications;
    private List<String> allergies;

    /**
     * Gets the first name of the person.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the person.
     *
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the person.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the person.
     *
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the birthdate of the person.
     *
     * @return the birthdate
     */
    public String getBirthdate() {
        return birthdate;
    }

    /**
     * Sets the birthdate of the person.
     *
     * @param birthdate the birthdate to set
     */
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * Gets the list of allergies.
     *
     * @return the allergies
     */
    public List<String> getAllergies() {
        return allergies;
    }

    /**
     * Sets the list of allergies.
     *
     * @param allergies the allergies to set
     */
    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    /**
     * Gets the list of medications.
     *
     * @return the medications
     */
    public List<String> getMedications() {
        return medications;
    }

    /**
     * Sets the list of medications.
     *
     * @param medications the medications to set
     */
    public void setMedications(List<String> medications) {
        this.medications = medications;
    }
}
