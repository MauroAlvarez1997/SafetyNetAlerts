package com.openclassrooms.SafetyNetAlerts.repository;

import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MedicalRecordRepository {

    private final JsonDataRepository jsonRepo;

    public MedicalRecordRepository(JsonDataRepository jsonRepo) {
        this.jsonRepo = jsonRepo;
    }

    public List<MedicalRecord> findAll() {
        // Accessing the medicalrecords list from the JSON wrapper
        return jsonRepo.getData().getMedicalRecords();
    }

    public Optional<MedicalRecord> findByName(String firstName, String lastName) {
        return jsonRepo.getData().getMedicalRecords().stream()
                .filter(m ->
                        m.getFirstName().equalsIgnoreCase(firstName) &&
                                m.getLastName().equalsIgnoreCase(lastName)
                )
                .findFirst();
    }

    public void save(MedicalRecord medicalRecord) {
        jsonRepo.getData().getMedicalRecords().add(medicalRecord);
        jsonRepo.persist();
    }

    public void delete(MedicalRecord medicalRecord) {
        jsonRepo.getData().getMedicalRecords().remove(medicalRecord);
        jsonRepo.persist();
    }

    public void persist(){
        jsonRepo.persist();
    }
}