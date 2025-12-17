package com.openclassrooms.SafetyNetAlerts.controller;

import com.openclassrooms.SafetyNetAlerts.dto.PersonDTO;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import com.openclassrooms.SafetyNetAlerts.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

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
    @PutMapping
    public PersonDTO update(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestBody Person newData) {

        logger.info("PUT /person called for {} {}", firstName, lastName);
        PersonDTO updated = service.updatePerson(firstName, lastName, newData);
        logger.info("PUT /person updated {} {}", firstName, lastName);
        return updated;
    }

    @DeleteMapping
    public void delete(@RequestParam String firstName, @RequestParam String lastName) {
        logger.info("DELETE /person called for {} {}", firstName, lastName);
        service.deletePerson(firstName, lastName);
        logger.info("DELETE /person succeeded for {} {}", firstName, lastName);
    }
}
