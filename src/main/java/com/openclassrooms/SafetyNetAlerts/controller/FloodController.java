package com.openclassrooms.SafetyNetAlerts.controller;

import com.openclassrooms.SafetyNetAlerts.dto.FloodAddressDTO;
import com.openclassrooms.SafetyNetAlerts.service.FloodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * REST Controller that handles requests for flood-related emergency information.
 * This controller identifies households covered by specific fire stations to assist
 * in flood emergency planning.
 */
@RestController
@RequestMapping("/flood")
public class FloodController {

    private static final Logger logger = LoggerFactory.getLogger(FloodController.class);
    private final FloodService service;

    /**
     * Constructs a new FloodController with the required FloodService.
     *
     * @param service the service used to retrieve household data by station
     */
    public FloodController(FloodService service) {
        this.service = service;
    }

    /**
     * Retrieves a list of all households (grouped by address) served by the
     * specified fire stations.
     *
     * @param stations a comma-separated string of fire station numbers
     * @return a list of FloodAddressDTOs, each containing residents and their medical info
     */
    @GetMapping("/stations")
    public List<FloodAddressDTO> getFloodInfo(
            @RequestParam("stations") String stations) {

        logger.debug("Controller: processing flood info request for stations: {}", stations);

        List<String> stationList = Arrays.asList(stations.split(","));

        logger.debug("Controller: parsed {} stations for service call", stationList.size());

        return service.getHouseholdsByStations(stationList);
    }
}