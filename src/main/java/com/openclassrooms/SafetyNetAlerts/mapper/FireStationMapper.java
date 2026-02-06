package com.openclassrooms.SafetyNetAlerts.mapper;

import com.openclassrooms.SafetyNetAlerts.dto.FireStationDTO;
import com.openclassrooms.SafetyNetAlerts.model.FireStation;

/**
 * Mapper class to convert between FireStation entities and FireStationDTO objects.
 */
public class FireStationMapper {

    /**
     * Converts a FireStation entity to a FireStationDTO.
     *
     * @param fireStation the FireStation entity to convert
     * @return a FireStationDTO containing the same data
     */
    public static FireStationDTO toDto(FireStation fireStation) {
        return new FireStationDTO(
                fireStation.getAddress(),
                fireStation.getStation()
        );
    }
}
