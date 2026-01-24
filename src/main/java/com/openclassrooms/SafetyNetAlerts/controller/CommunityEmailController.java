package com.openclassrooms.SafetyNetAlerts.controller;

import com.openclassrooms.SafetyNetAlerts.service.CommunityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller providing email addresses of all residents
 * living in a specified city.
 */
@RestController
public class CommunityEmailController {

    private final CommunityService service;

    /**
     * Constructs a CommunityEmailController with the given service.
     *
     * @param service service used to retrieve community email data
     */
    public CommunityEmailController(CommunityService service) {
        this.service = service;
    }

    /**
     * Retrieves all email addresses for residents in the specified city.
     * Duplicate email addresses are removed.
     *
     * @param city city used to search for residents
     * @return list of email addresses
     */
    @GetMapping("/communityEmail")
    public List<String> getCommunityEmails(@RequestParam("city") String city) {
        return service.getEmailsByCity(city);
    }
}
