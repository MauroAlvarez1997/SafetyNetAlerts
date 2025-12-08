package com.openclassrooms.SafetyNetAlerts.service;

import com.openclassrooms.SafetyNetAlerts.dto.PersonDTO;
import com.openclassrooms.SafetyNetAlerts.exceptions.PersonNotFoundException;
import com.openclassrooms.SafetyNetAlerts.mapper.PersonMapper;
import com.openclassrooms.SafetyNetAlerts.model.*;
import com.openclassrooms.SafetyNetAlerts.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);
    private final PersonRepository repo;

    public PersonService(PersonRepository repo) {
        this.repo = repo;
    }

    // Business logic: return DTOs
    public List<PersonDTO> getAllPersons() {
        logger.debug("Service: fetching all persons");
        return repo.findAll().stream()
                .map(PersonMapper::toDto)
                .collect(Collectors.toList());
    }

    public PersonDTO addPerson(Person createDto) {
        logger.debug("Service: adding person {} {}", createDto.getFirstName(), createDto.getLastName());
        Person person = PersonMapper.fromCreateDto(createDto);
        repo.save(person);
        return PersonMapper.toDto(person);
    }

    public PersonDTO updatePerson(String firstName, String lastName, Person updateDto) {
        Person existingPerson = repo.findByName(firstName, lastName)
                .orElseThrow(() -> new PersonNotFoundException("Person not found: " + firstName + " " + lastName));
        logger.debug("Service: updating person {} {}", firstName, lastName);

        // update allowed fields
        existingPerson.setAddress(updateDto.getAddress());
        existingPerson.setCity(updateDto.getCity());
        existingPerson.setZip(updateDto.getZip());
        existingPerson.setPhone(updateDto.getPhone());
        existingPerson.setEmail(updateDto.getEmail());

        return PersonMapper.toDto(existingPerson);
    }

    public void deletePerson(String firstName, String lastName) {
        Person personToDelete = repo.findByName(firstName, lastName)
                .orElseThrow(() -> new PersonNotFoundException("Person not found: " + firstName + " " + lastName));
        logger.debug("Service: deleting person {} {}", firstName, lastName);
        repo.delete(personToDelete);
    }
}
