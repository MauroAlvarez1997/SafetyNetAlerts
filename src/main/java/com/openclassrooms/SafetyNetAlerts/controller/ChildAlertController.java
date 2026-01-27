package com.openclassrooms.SafetyNetAlerts.controller;

import com.openclassrooms.SafetyNetAlerts.dto.ChildAlertDTO;
import com.openclassrooms.SafetyNetAlerts.service.ChildAlertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller providing child alert information for a given address.
 * This endpoint identifies children living at an address and returns their household members.
 */
@RestController
@RequestMapping
public class ChildAlertController {

    private static final Logger logger = LoggerFactory.getLogger(ChildAlertController.class);

    private final ChildAlertService service;

    /**
     * Constructs a ChildAlertController with the given service.
     *
     * @param service service used to retrieve child alert data
     */
    public ChildAlertController(ChildAlertService service) {
        this.service = service;
    }

    /**
     * Retrieves all children living at the specified address.
     * For each child, the response includes their age and the list of other household members.
     * If no children are found, an empty response is returned.
     *
     * @param address address used to search for residents
     * @return list of children with their household members, or an empty response if none exist
     */
    @GetMapping("/childAlert")
    public List<ChildAlertDTO> getChildrenByAddress( @RequestParam("address") String address) {
        logger.info("GET / child alert called");
        return service.getChildrenByAddress(address);
    }
}
