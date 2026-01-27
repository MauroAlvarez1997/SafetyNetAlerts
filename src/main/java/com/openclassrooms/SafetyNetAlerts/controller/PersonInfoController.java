package com.openclassrooms.SafetyNetAlerts.controller;

import com.openclassrooms.SafetyNetAlerts.dto.PersonInfoDTO;
import com.openclassrooms.SafetyNetAlerts.service.PersonInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller providing endpoints to retrieve detailed personal and medical
 * information about residents based on their identity.
 */
@RestController
@RequestMapping
public class PersonInfoController {

    private static final Logger logger = LoggerFactory.getLogger(PersonInfoController.class);
    private final PersonInfoService service;

    /**
     * Constructs a PersonInfoController with the required service.
     *
     * @param service the service used to handle personal information business logic
     */
    public PersonInfoController(PersonInfoService service) {
        this.service = service;
    }

    /**
     * Retrieves a list of personal information (address, age, email, medical history)
     * for all residents with the specified last name.
     *
     * @param lastName the last name of the person(s) to search for
     * @return a list of PersonInfoDTO objects matching the last name
     */
    @GetMapping("/personInfo")
    public List<PersonInfoDTO> getPersonInfo(
            @RequestParam String lastName) {

        logger.debug("Controller: Request received for /personInfo with lastName: {}", lastName);

        return service.getPersonsByLastName(lastName);
    }
}