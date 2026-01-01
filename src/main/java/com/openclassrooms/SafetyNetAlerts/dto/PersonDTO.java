package com.openclassrooms.SafetyNetAlerts.dto;

/**
 * Data Transfer Object representing a person.
 * Contains personal and contact information used for data exchange
 * between application layers.
 */
public class PersonDTO {

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String zip;
    private String phone;
    private String email;

    /**
     * Default constructor.
     */
    public PersonDTO() {}

    /**
     * Constructs a PersonDTO with full personal information.
     *
     * @param firstName the first name of the person
     * @param lastName  the last name of the person
     * @param address   the street address
     * @param city      the city
     * @param zip       the ZIP code
     * @param phone     the phone number
     * @param email     the email address
     */
    public PersonDTO(String firstName, String lastName, String address, String city, String zip, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
    }

    /**
     * Returns the first name of the person.
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
     * Returns the street address of the person.
     *
     * @return the address
     */
    public String getAddress() { return address; }

    /**
     * Sets the street address of the person.
     *
     * @param address the address to set
     */
    public void setAddress(String address) { this.address = address; }

    /**
     * Returns the city where the person lives.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city where the person lives.
     *
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns the ZIP code of the person's city.
     *
     * @return the ZIP code
     */
    public String getZip() {
        return zip;
    }

    /**
     * Sets the ZIP code of the person's city.
     *
     * @param zip the ZIP code to set
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * Returns the phone number of the person.
     *
     * @return the phone number
     */
    public String getPhone() { return phone; }

    /**
     * Sets the phone number of the person.
     *
     * @param phone the phone number to set
     */
    public void setPhone(String phone) { this.phone = phone; }

    /**
     * Returns the email address of the person.
     *
     * @return the email address
     */
    public String getEmail() { return email; }

    /**
     * Sets the email address of the person.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) { this.email = email; }
}
