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
 * REST controller for managing medical records.
 * Provides endpoints to create, retrieve, update, and delete medical records.
 */
@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    private static final Logger logger = LoggerFactory.getLogger(MedicalRecordController.class);

    private final MedicalRecordService service;

    /**
     * Constructs a MedicalRecordController with the given service.
     *
     * @param service the medical record service used to handle business logic
     */
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

    /**
     * Creates a new medical record.
     *
     * @param medicalRecord the medical record data to create
     * @return the created medical record as a DTO
     */
    @PostMapping
    public MedicalRecordDTO create(@RequestBody MedicalRecord medicalRecord) {
        logger.info("POST /medicalRecord called for {} {}",
                medicalRecord.getFirstName(), medicalRecord.getLastName());
        MedicalRecordDTO created = service.addMedicalRecord(medicalRecord);
        logger.info("POST /medicalRecord created for {} {}",
                created.getFirstName(), created.getLastName());
        return created;
    }

    /**
     * Updates an existing medical record identified by first and last name.
     *
     * @param firstName the first name of the person
     * @param lastName  the last name of the person
     * @param newData   the new medical record data
     * @return the updated medical record as a DTO
     */
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

    /**
     * Deletes a medical record identified by first and last name.
     *
     * @param firstName the first name of the person
     * @param lastName  the last name of the person
     */
    @DeleteMapping("/{firstName}/{lastName}")
    public void delete(@PathVariable String firstName, @PathVariable String lastName) {
        logger.info("DELETE /medicalRecord called for {} {}", firstName, lastName);
        service.deleteMedicalRecord(firstName, lastName);
        logger.info("DELETE /medicalRecord succeeded for {} {}", firstName, lastName);
    }
}