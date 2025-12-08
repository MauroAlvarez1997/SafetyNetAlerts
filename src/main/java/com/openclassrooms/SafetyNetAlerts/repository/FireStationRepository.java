package com.openclassrooms.SafetyNetAlerts.repository;

import com.openclassrooms.SafetyNetAlerts.JsonDataLoader;
import com.openclassrooms.SafetyNetAlerts.model.FireStation;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FireStationRepository {

    private final JsonDataLoader loader;


    public FireStationRepository(JsonDataLoader loader) {
        this.loader = loader;
    }

    public List<FireStation> findAll(){
        return loader.getFireStations();
    }

    public Optional<FireStation> findFireStation(String address, String station) {
        return loader.getFireStations().stream()
                .filter(fireStation -> fireStation.getAddress().equalsIgnoreCase(address)
                        && fireStation.getStation().equalsIgnoreCase(station))
                .findFirst();
    }

    public void save(FireStation fireStation){
        loader.getFireStations().add(fireStation);
    }

    public void delete(FireStation fireStation){
        loader.getFireStations().remove(fireStation);
    }

}
