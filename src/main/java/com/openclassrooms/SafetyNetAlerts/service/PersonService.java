package com.openclassrooms.SafetyNetAlerts.service;

import com.openclassrooms.SafetyNetAlerts.dto.PersonDTO;
import com.openclassrooms.SafetyNetAlerts.exceptions.PersonNotFoundException;
import com.openclassrooms.SafetyNetAlerts.model.*;
import com.openclassrooms.SafetyNetAlerts.repository.JsonDataRepository;
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
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public PersonDTO addPerson(Person p) {
        logger.debug("Service: adding person {} {}", p.getFirstName(), p.getLastName());
        repo.save(p);
        return toDto(p);
    }

    public PersonDTO updatePerson(String firstName, String lastName, Person newData) {
        Person existingPerson = repo.findByName(firstName, lastName)
                .orElseThrow(() -> new PersonNotFoundException("Person not found: " + firstName + " " + lastName));
        logger.debug("Service: updating person {} {}", firstName, lastName);

        // update allowed fields
        existingPerson.setAddress(newData.getAddress());
        existingPerson.setCity(newData.getCity());
        existingPerson.setZip(newData.getZip());
        existingPerson.setPhone(newData.getPhone());
        existingPerson.setEmail(newData.getEmail());

        return toDto(existingPerson);
    }

    public void deletePerson(String firstName, String lastName) {
        Person personToDelete = repo.findByName(firstName, lastName)
                .orElseThrow(() -> new PersonNotFoundException("Person not found: " + firstName + " " + lastName));
        logger.debug("Service: deleting person {} {}", firstName, lastName);
        repo.delete(personToDelete);
    }

    private PersonDTO toDto(Person p) {
        return new PersonDTO(p.getFirstName(), p.getLastName(), p.getAddress(), p.getPhone(), p.getEmail());
    }
}
