package com.openclassrooms.SafetyNetAlerts.controller;

import com.openclassrooms.SafetyNetAlerts.dto.FireAddressDTO;
import com.openclassrooms.SafetyNetAlerts.service.CommunityService;
import com.openclassrooms.SafetyNetAlerts.service.FireService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller responsible for handling requests related to fire safety and
 * emergency response data based on specific addresses.
 */
@RestController
public class FireController {
    private static final Logger logger = LoggerFactory.getLogger(FireController.class);
    private final FireService service;

    /**
     * Constructs a new FireController with the required FireService.
     *
     * @param service the service used to process fire and address logic
     */
    @Autowired
    public FireController(FireService service) {
        this.service = service;
    }

    /**
     * Retrieves fire station information and a list of residents for a given address.
     * The response typically includes the station number serving the address and
     * the residents' details (names, phone numbers, ages, and medical history).
     *
     * @param address the specific street address to look up
     * @return a Data Transfer Object containing station info and resident details
     */
    @GetMapping("/fire")
    public FireAddressDTO getFireInfo(@RequestParam("address") String address) {
        logger.debug("Controller: fetching fire info for address: {}", address);
        return service.getFireInfoByAddress(address);
    }
}
