package com.openclassrooms.SafetyNetAlerts.model;

/**
 * Represents a person with basic personal information such as
 * name, address, contact details, and zip code.
 */
public class Person {

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
    public Person() {}

    /**
     * Constructs a new Person with all fields.
     *
     * @param firstName first name
     * @param lastName last name
     * @param address street address
     * @param city city
     * @param zip ZIP code
     * @param phone phone number
     * @param email email address
     */
    public Person(String firstName, String lastName, String address, String city,
                  String zip, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
    }

    /**
     * Gets the first name.
     *
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     *
     * @param firstName first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name.
     *
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     *
     * @param lastName last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the street address.
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the street address.
     *
     * @param address address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the city.
     *
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city.
     *
     * @param city city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the ZIP code.
     *
     * @return ZIP code
     */
    public String getZip() {
        return zip;
    }

    /**
     * Sets the ZIP code.
     *
     * @param zip ZIP code to set
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * Gets the phone number.
     *
     * @return phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number.
     *
     * @param phone phone number to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the email address.
     *
     * @return email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address.
     *
     * @param email email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
