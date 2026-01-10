package com.openclassrooms.SafetyNetAlerts.repository;

import com.openclassrooms.SafetyNetAlerts.model.FireStation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository class for managing FireStation objects.
 * Interacts with the underlying JSON data storage via JsonDataRepository.
 */
@Repository
public class FireStationRepository {

    private final JsonDataRepository jsonRepo;

    /**
     * Constructs a FireStationRepository using the given JSON data repository.
     *
     * @param jsonRepo repository that provides access to JSON data
     */
    public FireStationRepository(JsonDataRepository jsonRepo) {
        this.jsonRepo = jsonRepo;
    }

    /**
     * Retrieves all fire stations.
     *
     * @return list of all FireStation objects
     */
    public List<FireStation> findAll() {
        return jsonRepo.getData().getFireStations();
    }

    /**
     * Finds a fire station by its address.
     *
     * @param address the address to search for
     * @return an Optional containing the FireStation if found, otherwise empty
     */
    public Optional<FireStation> findByAddress(String address) {
        return jsonRepo.getData().getFireStations().stream()
                .filter(fireStation -> fireStation.getAddress().equalsIgnoreCase(address))
                .findFirst();
    }

    /**
     * Returns a list of {@link FireStation} objects that are assigned to the given station number.
     *
     * @param stationNumber the station number to search for (case-insensitive)
     * @return a list of fire stations matching the given station number; empty if none found
     */
    public List<FireStation> findByStation(String stationNumber) {
        return jsonRepo.getData().getFireStations().stream()
                .filter(fireStation -> fireStation.getStation().equalsIgnoreCase(stationNumber))
                .toList();
    }

    /**
     * Saves a new fire station to the JSON data store.
     *
     * @param fireStation the FireStation object to save
     */
    public void save(FireStation fireStation) {
        jsonRepo.getData().getFireStations().add(fireStation);
        jsonRepo.persist();
    }

    /**
     * Persists the current state of the fire station data to storage.
     */
    public void persist() {
        jsonRepo.persist();
    }

    /**
     * Deletes a fire station from the JSON data store.
     *
     * @param fireStation the FireStation object to delete
     */
    public void delete(FireStation fireStation) {
        jsonRepo.getData().getFireStations().remove(fireStation);
        jsonRepo.persist();
    }
}
