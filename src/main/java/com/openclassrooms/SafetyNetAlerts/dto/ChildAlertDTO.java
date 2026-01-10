package com.openclassrooms.SafetyNetAlerts.dto;

import java.util.List;

/**
 * Data Transfer Object representing a child living at a specific address
 * along with their household members.
 * <p>
 * Contains the child's first and last name, age, and a list of other household members
 * (siblings, parents, etc.) living at the same address.
 */
public class ChildAlertDTO {

    private String firstName;
    private String lastName;
    private int age;
    private List<HouseholdMemberDTO> householdMembers;

    /**
     * Default constructor.
     */
    public ChildAlertDTO() {}

    /**
     * Constructs a {@link ChildAlertDTO} with the given child details and household members.
     *
     * @param firstName the first name of the child
     * @param lastName the last name of the child
     * @param age the age of the child
     * @param householdMembers list of other household members living at the same address
     */
    public ChildAlertDTO(String firstName, String lastName, int age,
                         List<HouseholdMemberDTO> householdMembers) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.householdMembers = householdMembers;
    }

    /**
     * Returns the child's first name.
     *
     * @return the first name
     */
    public String getFirstName() { return firstName; }

    /**
     * Returns the child's last name.
     *
     * @return the last name
     */
    public String getLastName() { return lastName; }

    /**
     * Returns the child's age.
     *
     * @return the age
     */
    public int getAge() { return age; }

    /**
     * Returns the list of household members living at the same address.
     *
     * @return the list of household members
     */
    public List<HouseholdMemberDTO> getHouseholdMembers() { return householdMembers; }

    /**
     * Sets the child's first name.
     *
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) { this.firstName = firstName; }

    /**
     * Sets the child's last name.
     *
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) { this.lastName = lastName; }

    /**
     * Sets the child's age.
     *
     * @param age the age to set
     */
    public void setAge(int age) { this.age = age; }

    /**
     * Sets the list of household members living at the same address.
     *
     * @param householdMembers the list of household members to set
     */
    public void setHouseholdMembers(List<HouseholdMemberDTO> householdMembers) {
        this.householdMembers = householdMembers;
    }
}
