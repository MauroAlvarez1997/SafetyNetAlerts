package com.openclassrooms.SafetyNetAlerts.dto;

/**
 * Data Transfer Object representing a household member.
 * <p>
 * Used within child alert responses to list other members of the
 * same household as the child.
 */
public class HouseholdMemberDTO {

    private String firstName;
    private String lastName;

    /**
     * Default constructor.
     */
    public HouseholdMemberDTO() {}

    /**
     * Constructs a {@link HouseholdMemberDTO} with the given first and last names.
     *
     * @param firstName the first name of the household member
     * @param lastName the last name of the household member
     */
    public HouseholdMemberDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Returns the first name of the household member.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the household member.
     *
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of the household member.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the household member.
     *
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
