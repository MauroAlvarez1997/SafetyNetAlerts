package com.openclassrooms.SafetyNetAlerts.controller;

import com.openclassrooms.SafetyNetAlerts.service.FireStationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller providing a list of phone numbers for residents
 * covered by a specific fire station. This endpoint is useful for
 * sending emergency notifications to households served by the station.
 */
@RestController
@RequestMapping("/phoneAlert")
public class PhoneAlertController {

    private static final Logger logger = LoggerFactory.getLogger(PhoneAlertController.class);
    private final FireStationService service;

    /**
     * Constructs a PhoneAlertController with the given FireStationService.
     *
     * @param service service used to retrieve phone numbers for a fire station
     */
    public PhoneAlertController(FireStationService service) {
        this.service = service;
    }

    /**
     * Retrieves all phone numbers of residents served by the given fire station.
     * Duplicate phone numbers are removed in the response.
     *
     * @param stationNumber the number of the fire station used to search for residents
     * @return list of phone numbers for residents covered by the fire station
     */
    @GetMapping
    public List<String> getPhones(@RequestParam("firestation") String stationNumber) {
        logger.debug("Controller: fetching phone numbers for fire station: {}", stationNumber);

        List<String> phoneNumbers = service.getPhoneNumbersByStation(stationNumber);

        logger.debug("Controller: found {} phone numbers for station {}", phoneNumbers.size(), stationNumber);
        return phoneNumbers;
    }
}