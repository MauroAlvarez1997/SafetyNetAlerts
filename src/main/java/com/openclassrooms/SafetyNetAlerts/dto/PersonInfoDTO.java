package com.openclassrooms.SafetyNetAlerts.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * Data Transfer Object containing comprehensive information about a person.
 * This includes identity, contact details, and medical history (medications and allergies).
 * Primarily used for the /personInfo endpoint.
 */
public class PersonInfoDTO {

    private static final Logger logger = LoggerFactory.getLogger(PersonInfoDTO.class);

    private String firstName;
    private String lastName;
    private String address;
    private int age;
    private String email;
    private List<String> medications;
    private List<String> allergies;

    /**
     * Constructs a PersonInfoDTO with complete details.
     *
     * @param firstName   the person's first name
     * @param lastName    the person's last name
     * @param address     the person's physical address
     * @param age         the person's calculated age
     * @param email       the person's email address
     * @param medications a list of the person's current medications
     * @param allergies   a list of the person's known allergies
     */
    public PersonInfoDTO(String firstName, String lastName, String address, int age, String email,
                         List<String> medications, List<String> allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.age = age;
        this.email = email;
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
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the physical address
     */
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the age in years
     */
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the list of medications
     */
    public List<String> getMedications() {
        return medications;
    }

    /**
     * Sets the medication list and logs the count for debugging.
     * @param medications list of medication strings
     */
    public void setMedications(List<String> medications) {
        logger.debug("DTO: Setting {} medications for {} {}",
                (medications != null ? medications.size() : 0), firstName, lastName);
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