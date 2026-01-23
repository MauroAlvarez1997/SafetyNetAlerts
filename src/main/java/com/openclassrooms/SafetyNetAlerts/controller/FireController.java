package com.openclassrooms.SafetyNetAlerts.controller;

import com.openclassrooms.SafetyNetAlerts.dto.FireAddressDTO;
import com.openclassrooms.SafetyNetAlerts.service.FireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FireController {

    private final FireService service;

    @Autowired
    public FireController(FireService service) {
        this.service = service;
    }

    @GetMapping("/fire")
    public FireAddressDTO getFireInfo(@RequestParam("address") String address) {
        return service.getFireInfoByAddress(address);
    }
}
