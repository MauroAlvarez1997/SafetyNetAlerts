package com.openclassrooms.SafetyNetAlerts.mapper;

import com.openclassrooms.SafetyNetAlerts.dto.CreatePersonDTO;
import com.openclassrooms.SafetyNetAlerts.dto.PersonDTO;
import com.openclassrooms.SafetyNetAlerts.model.Person;

public class PersonMapper {

    public static PersonDTO toDto(Person p) {
        return new PersonDTO(
                p.getFirstName(),
                p.getLastName(),
                p.getPhone()
        );
    }

    public static Person fromCreateDto(CreatePersonDTO dto) {
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
