package com.openclassrooms.SafetyNetAlerts.controller;

import com.openclassrooms.SafetyNetAlerts.dto.FloodAddressDTO;
import com.openclassrooms.SafetyNetAlerts.service.FloodService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/flood")
public class FloodController {

    private final FloodService service;

    public FloodController(FloodService service) {
        this.service = service;
    }

    /**
     * GET /flood/stations?stations=1,2,3
     */
    @GetMapping("/stations")
    public List<FloodAddressDTO> getFloodInfo(
            @RequestParam("stations") String stations) {

        List<String> stationList = Arrays.asList(stations.split(","));
        return service.getHouseholdsByStations(stationList);
    }
}
