package com.openclassrooms.SafetyNetAlerts.mapper;

import com.openclassrooms.SafetyNetAlerts.dto.PersonDTO;
import com.openclassrooms.SafetyNetAlerts.model.Person;

/**
 * Mapper class to convert between Person entities and PersonDTO objects.
 */
public class PersonMapper {

    /**
     * Converts a Person entity to a PersonDTO.
     *
     * @param p the Person entity to convert
     * @return a PersonDTO containing the same data
     */
    public static PersonDTO toDto(Person p) {
        return new PersonDTO(
                p.getFirstName(),
                p.getLastName(),
                p.getAddress(),
                p.getCity(),
                p.getZip(),
                p.getPhone(),
                p.getEmail()
        );
    }
}
