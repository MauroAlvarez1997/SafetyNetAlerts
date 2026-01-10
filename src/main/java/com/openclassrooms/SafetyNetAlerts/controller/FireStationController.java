package com.openclassrooms.SafetyNetAlerts.controller;

import com.openclassrooms.SafetyNetAlerts.dto.FireStationCoverageDTO;
import com.openclassrooms.SafetyNetAlerts.dto.FireStationDTO;
import com.openclassrooms.SafetyNetAlerts.dto.PersonDTO;
import com.openclassrooms.SafetyNetAlerts.model.FireStation;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import com.openclassrooms.SafetyNetAlerts.service.FireStationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing fire station records.
 * Provides endpoints to create, update, and delete fire stations.
 */
@RestController
@RequestMapping("/firestation")
public class FireStationController {

    private static final Logger logger = LoggerFactory.getLogger(FireStationController.class);
    private final FireStationService service;

    /**
     * Constructs a FireStationController with the given service.
     *
     * @param service the fire station service used to handle business logic
     */
    public FireStationController(FireStationService service) {
        this.service = service;
    }

    /**
     * Retrieves all fire stations.
     *
     * @return a list of all fire station DTOs
     */
    @GetMapping
    public List<FireStationDTO> getAll() {
        logger.info("GET /firestation called");
        List<FireStationDTO> fireStations = service.getAllFireStations();
        logger.info("GET /firestation responded with {} items", fireStations.size());

        return fireStations;
    }

    /**
     * Creates a new fire station mapping.
     *
     * @param fireStation the fire station data to create
     * @return the created fire station as a DTO
     */
    @PostMapping
    public FireStationDTO create(@RequestBody FireStation fireStation) {
        logger.info("POST /firestation called for address {}",
                fireStation.getAddress());
        FireStationDTO created = service.addFireStation(fireStation);
        logger.info("POST /firestation created address {} with station {}",
                created.getAddress(), created.getStation());

        return created;
    }

    /**
     * Updates the fire station number associated with a given address.
     *
     * @param address the address to update
     * @param newData the new fire station data
     * @return the updated fire station as a DTO
     */
    @PutMapping("/{address}")
    public FireStationDTO update(
            @PathVariable String address,
            @RequestBody FireStation newData) {
        logger.info("PUT /firestation/{} called", address);
        FireStationDTO updated = service.updateFireStation(address, newData);
        logger.info("PUT /firestation/{} updated to station {}",
                address, updated.getStation());

        return updated;
    }


    /**
     * Deletes a fire station mapping for a given address.
     *
     * @param address the address of the fire station to delete
     */
    @DeleteMapping("/{address}")
    public void delete(@PathVariable String address) {
        logger.info("DELETE /firestation/{} called", address);
        service.deleteFireStation(address);
        logger.info("DELETE /firestation/{} succeeded", address);
    }

    /**
     * Retrieves all residents covered by the specified fire station.
     * The response includes the list of residents along with the number of adults and children.
     *
     * @param stationNumber fire station number used to identify the coverage area
     * @return fire station coverage data including residents and population counts
     */
    @GetMapping(params = "stationNumber")
    public FireStationCoverageDTO getCoverage(
            @RequestParam String stationNumber) {

        logger.info("GET /firestation?stationNumber={} called", stationNumber);
        return service.getCoverageByStationNumber(stationNumber);
    }

}

