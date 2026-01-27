package com.openclassrooms.SafetyNetAlerts.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * Data Transfer Object representing emergency information for a specific address.
 * It contains the station number responsible for the area and a detailed list
 * of residents, including their medical information.
 */
public class FireAddressDTO {

    private static final Logger logger = LoggerFactory.getLogger(FireAddressDTO.class);
    private String stationNumber;
    private List<ResidentFireDTO> residents;

    /**
     * Default constructor for JSON deserialization and reflection-based frameworks.
     */
    public FireAddressDTO() {}

    /**
     * Constructs a FireAddressDTO with the specified station number and resident list.
     *
     * @param stationNumber the identifier of the fire station serving the address
     * @param residents    the list of residents located at the address
     */
    public FireAddressDTO(String stationNumber, List<ResidentFireDTO> residents) {
        this.stationNumber = stationNumber;
        this.residents = residents;
    }

    /**
     * Gets the fire station number serving the address.
     *
     * @return the station number string
     */
    public String getStationNumber() {
        return stationNumber;
    }

    /**
     * Sets the fire station number for this address response.
     *
     * @param stationNumber the station number to set
     */
    public void setStationNumber(String stationNumber) {
        logger.debug("DTO: Setting stationNumber to {}", stationNumber);
        this.stationNumber = stationNumber;
    }

    /**
     * Gets the list of residents associated with the address.
     *
     * @return a list of ResidentFireDTO objects
     */
    public List<ResidentFireDTO> getResidents() {
        return residents;
    }

    /**
     * Sets the list of residents for this address response.
     *
     * @param residents the list of residents to set
     */
    public void setResidents(List<ResidentFireDTO> residents) {
        logger.debug("DTO: Setting residents list for fire station: {}", this.stationNumber);
        this.residents = residents;
    }
}