package com.openclassrooms.SafetyNetAlerts.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * Data Transfer Object representing a resident's details specifically for flood alerts.
 * This structure groups residents by their household and provides essential medical
 * info to emergency services during flood response scenarios.
 */
public class ResidentFloodDTO {

    private static final Logger logger = LoggerFactory.getLogger(ResidentFloodDTO.class);

    private String firstName;
    private String lastName;
    private String phone;
    private int age;
    private List<String> medications;
    private List<String> allergies;

    /**
     * Default constructor for JSON deserialization.
     */
    public ResidentFloodDTO() {}

    /**
     * Constructs a ResidentFloodDTO with identity and medical details.
     *
     * @param firstName   the resident's first name
     * @param lastName    the resident's last name
     * @param phone       the resident's phone number
     * @param age         the resident's age
     * @param medications a list of current medications
     * @param allergies   a list of known allergies
     */
    public ResidentFloodDTO(String firstName, String lastName, String phone,
                            int age, List<String> medications, List<String> allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.age = age;
        this.medications = medications;
        this.allergies = allergies;
    }

    /**
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the list of known allergies
     */
    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    /**
     * @return the list of current medications
     */
    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    /**
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the phone number
     */
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the calculated age
     */
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        logger.debug("DTO: Mapping data for resident: {} {}, age: {}", firstName, lastName, age);
        this.age = age;
    }
}