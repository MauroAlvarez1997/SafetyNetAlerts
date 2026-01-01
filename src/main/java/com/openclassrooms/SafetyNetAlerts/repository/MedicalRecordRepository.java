package com.openclassrooms.SafetyNetAlerts.repository;

import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository class for managing MedicalRecord objects.
 * Interacts with the underlying JSON data store via {@link JsonDataRepository}.
 */
@Repository
public class MedicalRecordRepository {

    private final JsonDataRepository jsonRepo;

    /**
     * Constructs a MedicalRecordRepository using the given JSON data repository.
     *
     * @param jsonRepo repository that provides access to JSON data
     */
    public MedicalRecordRepository(JsonDataRepository jsonRepo) {
        this.jsonRepo = jsonRepo;
    }

    /**
     * Retrieves all medical records.
     *
     * @return list of all {@link MedicalRecord} objects
     */
    public List<MedicalRecord> findAll() {
        return jsonRepo.getData().getMedicalRecords();
    }

    /**
     * Finds a medical record by the first and last name of the person.
     *
     * @param firstName first name of the person
     * @param lastName  last name of the person
     * @return an Optional containing the {@link MedicalRecord} if found, otherwise empty
     */
    public Optional<MedicalRecord> findByName(String firstName, String lastName) {
        return jsonRepo.getData().getMedicalRecords().stream()
                .filter(m ->
                        m.getFirstName().equalsIgnoreCase(firstName) &&
                                m.getLastName().equalsIgnoreCase(lastName)
                )
                .findFirst();
    }

    /**
     * Saves a new medical record to the JSON data store.
     *
     * @param medicalRecord the {@link MedicalRecord} object to save
     */
    public void save(MedicalRecord medicalRecord) {
        jsonRepo.getData().getMedicalRecords().add(medicalRecord);
        jsonRepo.persist();
    }

    /**
     * Deletes a medical record from the JSON data store.
     *
     * @param medicalRecord the {@link MedicalRecord} object to delete
     */
    public void delete(MedicalRecord medicalRecord) {
        jsonRepo.getData().getMedicalRecords().remove(medicalRecord);
        jsonRepo.persist();
    }

    /**
     * Persists the current state of the medical records data to storage.
     */
    public void persist() {
        jsonRepo.persist();
    }
}