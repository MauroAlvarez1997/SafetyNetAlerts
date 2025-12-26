package com.openclassrooms.SafetyNetAlerts.controller;

import com.openclassrooms.SafetyNetAlerts.dto.MedicalRecordDTO;
import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;
import com.openclassrooms.SafetyNetAlerts.service.MedicalRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handle request for medical record objects
 */
@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    private static final Logger logger = LoggerFactory.getLogger(MedicalRecordController.class);

    private final MedicalRecordService service;

    @Autowired
    public MedicalRecordController(MedicalRecordService service) {
        this.service = service;
    }

    /**
     * fetch all medical records
     *
     * @return List of medical record DTO's
     */
    @GetMapping
    public List<MedicalRecordDTO> getAll() {
        logger.info("GET /medicalRecord called");
        List<MedicalRecordDTO> res = service.getAllMedicalRecords();
        logger.info("GET /medicalRecord responded with {} items", res.size());
        return res;
    }

    @PostMapping
    public MedicalRecordDTO create(@RequestBody MedicalRecord medicalRecord) {
        logger.info("POST /medicalRecord called for {} {}",
                medicalRecord.getFirstName(), medicalRecord.getLastName());
        MedicalRecordDTO created = service.addMedicalRecord(medicalRecord);
        logger.info("POST /medicalRecord created for {} {}",
                created.getFirstName(), created.getLastName());
        return created;
    }

    @PutMapping("/{firstName}/{lastName}")
    public MedicalRecordDTO update(
            @PathVariable String firstName,
            @PathVariable String lastName,
            @RequestBody MedicalRecord newData) {

        logger.info("PUT /medicalRecord called for {} {}", firstName, lastName);
        MedicalRecordDTO updated = service.updateMedicalRecord(firstName, lastName, newData);
        logger.info("PUT /medicalRecord updated for {} {}", firstName, lastName);
        return updated;
    }

    @DeleteMapping("/{firstName}/{lastName}")
    public void delete(@PathVariable String firstName, @PathVariable String lastName) {
        logger.info("DELETE /medicalRecord called for {} {}", firstName, lastName);
        service.deleteMedicalRecord(firstName, lastName);
        logger.info("DELETE /medicalRecord succeeded for {} {}", firstName, lastName);
    }
}