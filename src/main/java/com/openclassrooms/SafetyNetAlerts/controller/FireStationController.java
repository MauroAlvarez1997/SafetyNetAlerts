package com.openclassrooms.SafetyNetAlerts.controller;

import com.openclassrooms.SafetyNetAlerts.dto.FireStationDTO;
import com.openclassrooms.SafetyNetAlerts.dto.PersonDTO;
import com.openclassrooms.SafetyNetAlerts.model.FireStation;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import com.openclassrooms.SafetyNetAlerts.service.FireStationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/firestation")
public class FireStationController {

    private static final Logger logger = LoggerFactory.getLogger(FireStationController.class);
    private final FireStationService service;

    public FireStationController(FireStationService service) {
        this.service = service;
    }

    @GetMapping
    public List<FireStationDTO> getAll() {
        logger.info("GET /firestation called");
        List<FireStationDTO> fireStations = service.getAllFireStations();
        logger.info("GET /firestation responded with {} items", fireStations.size());

        return fireStations;
    }

    @PostMapping
    public FireStationDTO create(@RequestBody FireStation fireStation) {
        logger.info("POST /firestation called for address {}",
                fireStation.getAddress());
        FireStationDTO created = service.addFireStation(fireStation);
        logger.info("POST /firestation created address {} with station {}",
                created.getAddress(), created.getStation());

        return created;
    }

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

    @DeleteMapping("/{address}")
    public void delete(@PathVariable String address) {
        logger.info("DELETE /firestation/{} called", address);
        service.deleteFireStation(address);
        logger.info("DELETE /firestation/{} succeeded", address);
    }
}

