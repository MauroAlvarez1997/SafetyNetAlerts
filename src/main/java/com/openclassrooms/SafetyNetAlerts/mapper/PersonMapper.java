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

    /**
     * Converts a Person object (currently passed as a Person entity) to a new Person entity.
     *
     * @param dto the Person object containing data (ideally should be a DTO)
     * @return a new Person entity with the same data
     */
    public static Person fromCreateDto(Person dto) {
        Person p = new Person();
        p.setFirstName(dto.getFirstName());
        p.setLastName(dto.getLastName());
        p.setAddress(dto.getAddress());
        p.setCity(dto.getCity());
        p.setZip(dto.getZip());
        p.setPhone(dto.getPhone());
        p.setEmail(dto.getEmail());
        return p;
    }
}
