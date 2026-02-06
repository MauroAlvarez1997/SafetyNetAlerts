package com.openclassrooms.SafetyNetAlerts.service;

import com.openclassrooms.SafetyNetAlerts.dto.PersonDTO;
import com.openclassrooms.SafetyNetAlerts.exceptions.PersonNotFoundException;
import com.openclassrooms.SafetyNetAlerts.mapper.PersonMapper;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import com.openclassrooms.SafetyNetAlerts.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer for managing {@link Person} objects.
 * Handles business logic and communicates with {@link PersonRepository}.
 */
@Service
public class PersonService {
    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);
    private final PersonRepository repo;

    /**
     * Constructs a PersonService with the given repository.
     *
     * @param repo repository for person data
     */
    @Autowired
    public PersonService(PersonRepository repo) {
        this.repo = repo;
    }

    /**
     * Retrieves all persons and maps them to DTOs.
     *
     * @return list of {@link PersonDTO} objects
     */
    public List<PersonDTO> getAllPersons() {
        logger.debug("Service: fetching all persons");
        return repo.findAll().stream()
                .map(PersonMapper::toDto)
                .toList();
    }

    /**
     * Adds a new person.
     *
     * @param person person data to add
     * @return the created {@link PersonDTO}
     */
    public PersonDTO addPerson(Person person) {
        logger.debug("Service: adding person {} {}", person.getFirstName(), person.getLastName());
        repo.save(person);
        return PersonMapper.toDto(person);
    }

    /**
     * Updates an existing person by first and last name.
     *
     * @param firstName first name of the person to update
     * @param lastName  last name of the person to update
     * @param updateDto updated person data
     * @return the updated {@link PersonDTO}
     * @throws PersonNotFoundException if no person exists for the given name
     */
    public PersonDTO updatePerson(String firstName, String lastName, Person updateDto) {
        Person existingPerson = repo.findByName(firstName, lastName)
                .orElseThrow(() -> new PersonNotFoundException(
                        "Person not found: " + firstName + " " + lastName));
        logger.debug("Service: updating person {} {}", firstName, lastName);

        // update allowed fields
        existingPerson.setAddress(updateDto.getAddress());
        existingPerson.setCity(updateDto.getCity());
        existingPerson.setZip(updateDto.getZip());
        existingPerson.setPhone(updateDto.getPhone());
        existingPerson.setEmail(updateDto.getEmail());

        repo.persist();

        return PersonMapper.toDto(existingPerson);
    }

    /**
     * Deletes a person by first and last name.
     *
     * @param firstName first name of the person to delete
     * @param lastName  last name of the person to delete
     * @throws PersonNotFoundException if no person exists for the given name
     */
    public void deletePerson(String firstName, String lastName) {
        Person personToDelete = repo.findByName(firstName, lastName)
                .orElseThrow(() -> new PersonNotFoundException(
                        "Person not found: " + firstName + " " + lastName));
        logger.debug("Service: deleting person {} {}", firstName, lastName);
        repo.delete(personToDelete);
    }
}
