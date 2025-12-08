package com.openclassrooms.SafetyNetAlerts.mapper;

import com.openclassrooms.SafetyNetAlerts.dto.FireStationDTO;
import com.openclassrooms.SafetyNetAlerts.model.FireStation;


public class FireStationMapper {

    public static FireStationDTO toDto(FireStation fireStation) {
        return new FireStationDTO(
                fireStation.getAddress(),
                fireStation.getStation()
        );
    }

    public static FireStation fromCreateDto(FireStation dto) {
        FireStation fireStation = new FireStation();
        fireStation.setAddress(dto.getAddress());
        fireStation.setStation(dto.getStation());
        return fireStation;
    }
}
