package com.openclassrooms.SafetyNetAlerts.service;

import com.openclassrooms.SafetyNetAlerts.dto.MedicalRecordDTO;
import com.openclassrooms.SafetyNetAlerts.exceptions.MedicalRecordNotFoundException;
import com.openclassrooms.SafetyNetAlerts.mapper.MedicalRecordMapper;
import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;
import com.openclassrooms.SafetyNetAlerts.repository.MedicalRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicalRecordService {
    private static final Logger logger = LoggerFactory.getLogger(MedicalRecordService.class);
    private final MedicalRecordRepository repo;

    @Autowired
    public MedicalRecordService(MedicalRecordRepository repo) {
        this.repo = repo;
    }

    // Business logic: return DTOs
    public List<MedicalRecordDTO> getAllMedicalRecords() {
        logger.debug("Service: fetching all medical records");
        return repo.findAll().stream()
                .map(MedicalRecordMapper::toDto)
                .collect(Collectors.toList());
    }

    public MedicalRecordDTO addMedicalRecord(MedicalRecord medicalRecord) {
        logger.debug("Service: adding medical record for {} {}",
                medicalRecord.getFirstName(), medicalRecord.getLastName());
        repo.save(medicalRecord);
        return MedicalRecordMapper.toDto(medicalRecord);
    }

    public MedicalRecordDTO updateMedicalRecord(String firstName, String lastName, MedicalRecord updateDto) {
        MedicalRecord existingRecord = repo.findByName(firstName, lastName)
                .orElseThrow(() -> new MedicalRecordNotFoundException("Medical record not found for: " + firstName + " " + lastName));

        logger.debug("Service: updating medical record for {} {}", firstName, lastName);

        // Update medical-specific fields
        existingRecord.setBirthdate(updateDto.getBirthdate());
        existingRecord.setMedications(updateDto.getMedications());
        existingRecord.setAllergies(updateDto.getAllergies());

        repo.persist(); // Saving changes to the data source

        return MedicalRecordMapper.toDto(existingRecord);
    }

    public void deleteMedicalRecord(String firstName, String lastName) {
        MedicalRecord recordToDelete = repo.findByName(firstName, lastName)
                .orElseThrow(() -> new MedicalRecordNotFoundException("Medical record not found for: " + firstName + " " + lastName));

        logger.debug("Service: deleting medical record for {} {}", firstName, lastName);
        repo.delete(recordToDelete);
    }
}