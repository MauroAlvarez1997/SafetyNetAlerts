package com.openclassrooms.SafetyNetAlerts.dto;

/**
 * Data Transfer Object representing a resident.
 * <p>
 * Contains basic information about a resident such as first name, last name,
 * address, and phone number. Used in responses for endpoints like
 * fire station coverage.
 */
public class ResidentDTO {

    private String firstName;
    private String lastName;
    private String address;
    private String phone;

    /**
     * Default constructor.
     */
    public ResidentDTO() {}

    /**
     * Constructs a {@link ResidentDTO} with all fields initialized.
     *
     * @param firstName the first name of the resident
     * @param lastName the last name of the resident
     * @param address the address of the resident
     * @param phone the phone number of the resident
     */
    public ResidentDTO(String firstName, String lastName, String address, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }

    /**
     * Returns the first name of the resident.
     *
     * @return the resident's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the resident.
     *
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of the resident.
     *
     * @return the resident's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the resident.
     *
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the address of the resident.
     *
     * @return the resident's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the resident.
     *
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the phone number of the resident.
     *
     * @return the resident's phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the resident.
     *
     * @param phone the phone number to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
