package com.openclassrooms.SafetyNetAlerts.mapper;

import com.openclassrooms.SafetyNetAlerts.dto.ChildAlertDTO;
import com.openclassrooms.SafetyNetAlerts.dto.HouseholdMemberDTO;
import com.openclassrooms.SafetyNetAlerts.model.Person;

import java.util.List;

/**
 * Utility class for mapping {@link Person} objects to {@link ChildAlertDTO} and
 * {@link HouseholdMemberDTO} objects.
 * <p>
 * This class provides static mapping methods only and should not be instantiated.
 */
public class ChildAlertMapper {

    private ChildAlertMapper() {}

    /**
     * Converts a {@link Person} representing a child into a {@link ChildAlertDTO}.
     *
     * @param child the {@link Person} representing the child
     * @param age the calculated age of the child
     * @param householdMembers a list of {@link HouseholdMemberDTO} representing other household members
     * @return a {@link ChildAlertDTO} containing the child's first name, last name, age, and household members
     */
    public static ChildAlertDTO toDto(
            Person child,
            int age,
            List<HouseholdMemberDTO> householdMembers) {

        return new ChildAlertDTO(
                child.getFirstName(),
                child.getLastName(),
                age,
                householdMembers
        );
    }

    /**
     * Converts a {@link Person} into a {@link HouseholdMemberDTO}.
     *
     * @param person the {@link Person} to convert
     * @return a {@link HouseholdMemberDTO} containing the person's first and last name
     */
    public static HouseholdMemberDTO toHouseholdMember(Person person) {
        return new HouseholdMemberDTO(
                person.getFirstName(),
                person.getLastName()
        );
    }
}
