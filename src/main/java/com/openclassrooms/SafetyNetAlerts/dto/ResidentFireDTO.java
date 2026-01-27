package com.openclassrooms.SafetyNetAlerts.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * Data Transfer Object representing a resident's details specifically for fire emergencies.
 * This includes identity, contact information, and medical background to assist first responders.
 */
public class ResidentFireDTO {

    private static final Logger logger = LoggerFactory.getLogger(ResidentFireDTO.class);

    private String firstName;
    private String lastName;
    private String phone;
    private int age;
    private List<String> medications;
    private List<String> allergies;

    /**
     * Default constructor for JSON deserialization.
     */
    public ResidentFireDTO() {}

    /**
     * Constructs a ResidentFireDTO with full identity and medical details.
     *
     * @param firstName   the resident's first name
     * @param lastName    the resident's last name
     * @param phone       the resident's phone number
     * @param age         the resident's age
     * @param medications a list of the resident's current medications
     * @param allergies   a list of the resident's known allergies
     */
    public ResidentFireDTO(String firstName, String lastName, String phone,
                           int age, List<String> medications, List<String> allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.age = age;
        this.medications = medications;
        this.allergies = allergies;
    }

    /**
     * @return the resident's first name
     */
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the resident's last name
     */
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the resident's phone number
     */
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the resident's age
     */
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        logger.debug("DTO: Setting age to {} for resident {} {}", age, firstName, lastName);
        this.age = age;
    }

    /**
     * @return the list of medications
     */
    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    /**
     * @return the list of allergies
     */
    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }
}