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
    public List<FireStationDTO> getAll(){
        logger.info("GET / fire stations called");
        List<FireStationDTO> fireStations = service.getAllFireStations();
        logger.info("GET / fire stations responded with {} items", fireStations.size());
        return fireStations;
    }

    @PostMapping
    public FireStationDTO create(@RequestBody FireStation fireStation){
        logger.info("POST / fire station called for {} {}", fireStation.getAddress(), fireStation.getStation());
        FireStationDTO createdFireStation = service.addFireStation(fireStation);
        logger.info("POST / fire station created {} {}", createdFireStation.getAddress(), createdFireStation.getStation());
        return createdFireStation;
    }

    @PutMapping
    public FireStationDTO update( @RequestParam String address, @RequestParam String station, @RequestBody FireStation newData){
        logger.info("PUT / fire station called for {} {}", address, station);
        FireStationDTO updatedFireStation = service.updateFireStation(address, station, newData);
        logger.info("PUT / fire station updated {} {}", address, station);
        return updatedFireStation;
    }

    @DeleteMapping
    public void delete(@RequestParam String address, @RequestParam String station) {
        logger.info("DELETE / fire station called {} {}", address, station);
        service.deleteFireStation(address, station);
        logger.info("DELETE / fire station succeeded for {} {}", address, station);
    }
}
