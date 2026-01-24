package com.openclassrooms.SafetyNetAlerts.controller;

import com.openclassrooms.SafetyNetAlerts.dto.PersonInfoDTO;
import com.openclassrooms.SafetyNetAlerts.service.PersonInfoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class PersonInfoController {

    private final PersonInfoService service;

    public PersonInfoController(PersonInfoService service) {
        this.service = service;
    }

    @GetMapping("/personInfo")
    public List<PersonInfoDTO> getPersonInfo(
            @RequestParam String lastName) {

        return service.getPersonsByLastName(lastName);
    }
}
