package com.openclassrooms.SafetyNetAlerts.dto;

import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;

import java.util.List;

/**
 * Data Transfer Object representing a medical record.
 * Contains personal and medical information used for data exchange
 * without exposing internal domain models.
 */
public class MedicalRecordDTO {
    private String firstName;
    private String lastName;
    private String birthdate;
    private List<String> medications;
    private List<String> allergies;

    /**
     * Default constructor.
     */
    public MedicalRecordDTO(){}

    /**
     * Constructs a MedicalRecordDTO with full medical information.
     *
     * @param firstName   the first name of the person
     * @param lastName    the last name of the person
     * @param birthdate   the birthdate of the person
     * @param medications the list of medications
     * @param allergies   the list of allergies
     */
    public MedicalRecordDTO(String firstName, String lastName, String birthdate, List<String> medications, List<String> allergies){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.medications = medications;
        this.allergies = allergies;
    }


    /**
     * Returns the first name of the person.
     *
     * @return the first name
     */
    public String getFirstName() { return firstName; }


    /**
     * Sets the first name of the person.
     *
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of the person.
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
     * Returns the birthdate of the person.
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
     * Returns the list of medications.
     *
     * @return the medications list
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

    /**
     * Returns the list of allergies.
     *
     * @return the allergies list
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

}
