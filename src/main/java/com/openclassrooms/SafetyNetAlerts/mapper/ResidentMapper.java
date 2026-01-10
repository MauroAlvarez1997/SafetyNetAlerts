package com.openclassrooms.SafetyNetAlerts.mapper;

import com.openclassrooms.SafetyNetAlerts.dto.ResidentDTO;
import com.openclassrooms.SafetyNetAlerts.model.Person;

/**
 * Utility class for mapping {@link Person} objects to {@link ResidentDTO} objects.
 * <p>
 * This class provides static methods only and should not be instantiated.
 */
public class ResidentMapper {

    private ResidentMapper() {}

    /**
     * Converts a {@link Person} entity to a {@link ResidentDTO}.
     *
     * @param person the {@link Person} to convert
     * @return a {@link ResidentDTO} containing the first name, last name, address, and phone of the person
     */
    public static ResidentDTO toDto(Person person) {
        return new ResidentDTO(
                person.getFirstName(),
                person.getLastName(),
                person.getAddress(),
                person.getPhone()
        );
    }
}
