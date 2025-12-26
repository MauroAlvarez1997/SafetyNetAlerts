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
 * Handle request for person objects
 */
@RestController
@RequestMapping("/persons")
public class PersonController {

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    private final PersonService service;

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

    @PostMapping
    public PersonDTO create(@RequestBody Person person) {
        logger.info("POST /person called for {} {}", person.getFirstName(), person.getLastName());
        PersonDTO created = service.addPerson(person);
        logger.info("POST /person created {} {}", created.getFirstName(), created.getLastName());
        return created;
    }

    // Update via query params firstName & lastName (spec says unique identifier)
    //use @PathParam, look into how to do it with identifier
    //assume first and last name doenst change
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

    @DeleteMapping("/{firstName}/{lastName}")
    public void delete(@PathVariable String firstName, @PathVariable String lastName) {
        logger.info("DELETE /person called for {} {}", firstName, lastName);
        service.deletePerson(firstName, lastName);
        logger.info("DELETE /person succeeded for {} {}", firstName, lastName);
    }
}
