package com.openclassrooms.SafetyNetAlerts.repository;

import com.openclassrooms.SafetyNetAlerts.JsonDataLoader;
import com.openclassrooms.SafetyNetAlerts.model.FireStation;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FireStationRepository {

    private final JsonDataRepository jsonRepo;

    public FireStationRepository(JsonDataRepository jsonRepo) {
        this.jsonRepo = jsonRepo;
    }

    public List<FireStation> findAll() {
        return jsonRepo.getData().getFireStations();
    }

    public Optional<FireStation> findByAddress(String address) {
        return jsonRepo.getData().getFireStations().stream()
                .filter(fireStation -> fireStation.getAddress().equalsIgnoreCase(address))
                .findFirst();
    }

    public void save(FireStation fireStation) {
        jsonRepo.getData().getFireStations().add(fireStation);
        jsonRepo.persist();
    }

    public void persist() {
        jsonRepo.persist();
    }

    public void delete(FireStation fireStation) {
        jsonRepo.getData().getFireStations().remove(fireStation);
        jsonRepo.persist();
    }

}
