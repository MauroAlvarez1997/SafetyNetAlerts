package com.openclassrooms.SafetyNetAlerts.controller;

import com.openclassrooms.SafetyNetAlerts.dto.PersonDTO;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import com.openclassrooms.SafetyNetAlerts.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing persons.
 * Provides endpoints to create, retrieve, update, and delete person data.
 */
@RestController
@RequestMapping("/persons")
public class PersonController {

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    private final PersonService service;

    /**
     * Constructs a PersonController with the given service.
     *
     * @param service the person service used to handle business logic
     */
    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    /**
     * fetch all people
     *
     * @return List of people DTO's
     */
    @GetMapping
    public List<PersonDTO> getAll() {
        logger.info("GET /person called");
        List<PersonDTO> res = service.getAllPersons();
        logger.info("GET /person responded with {} items", res.size());
        return res;
    }

    /**
     * Creates a new person.
     *
     * @param person the person data to create
     * @return the created person as a DTO
     */
    @PostMapping
    public PersonDTO create(@RequestBody Person person) {
        logger.info("POST /person called for {} {}", person.getFirstName(), person.getLastName());
        PersonDTO created = service.addPerson(person);
        logger.info("POST /person created {} {}", created.getFirstName(), created.getLastName());
        return created;
    }

    /**
     * Updates an existing person identified by first and last name.
     *
     * @param firstName the first name of the person
     * @param lastName  the last name of the person
     * @param newData   the new person data
     * @return the updated person as a DTO
     */
    @PutMapping("/{firstName}/{lastName}")
    public PersonDTO update(
            @PathVariable String firstName,
            @PathVariable String lastName,
            @RequestBody Person newData) {

        logger.info("PUT /person called for {} {}", firstName, lastName);
        PersonDTO updated = service.updatePerson(firstName, lastName, newData);
        logger.info("PUT /person updated {} {}", firstName, lastName);
        return updated;
    }

    /**
     * Deletes a person identified by first and last name.
     *
     * @param firstName the first name of the person
     * @param lastName  the last name of the person
     */
    @DeleteMapping("/{firstName}/{lastName}")
    public void delete(@PathVariable String firstName, @PathVariable String lastName) {
        logger.info("DELETE /person called for {} {}", firstName, lastName);
        service.deletePerson(firstName, lastName);
        logger.info("DELETE /person succeeded for {} {}", firstName, lastName);
    }
}
