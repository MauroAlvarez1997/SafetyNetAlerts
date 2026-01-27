package com.openclassrooms.SafetyNetAlerts.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * Data Transfer Object representing a household grouped by its address.
 * Used primarily in flood alerts to list all residents at a specific location
 * along with their medical information.
 */
public class FloodAddressDTO {

    private static final Logger logger = LoggerFactory.getLogger(FloodAddressDTO.class);
    private String address;
    private List<ResidentFloodDTO> residents;

    /**
     * Default constructor for JSON deserialization.
     */
    public FloodAddressDTO() {}

    /**
     * Constructs a FloodAddressDTO with a specific address and its list of residents.
     *
     * @param address   the street address of the household
     * @param residents the list of residents living at this address
     */
    public FloodAddressDTO(String address, List<ResidentFloodDTO> residents) {
        this.address = address;
        this.residents = residents;
    }

    /**
     * Gets the street address of the household.
     *
     * @return the address string
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the street address for this household.
     *
     * @param address the address string to set
     */
    public void setAddress(String address) {
        logger.debug("DTO: Setting address to {}", address);
        this.address = address;
    }

    /**
     * Gets the list of residents associated with this address.
     *
     * @return a list of ResidentFloodDTO objects
     */
    public List<ResidentFloodDTO> getResidents() {
        return residents;
    }

    /**
     * Sets the list of residents for this household.
     *
     * @param residents the list of ResidentFloodDTOs to set
     */
    public void setResidents(List<ResidentFloodDTO> residents) {
        logger.debug("DTO: Setting residents list for address: {}", this.address);
        this.residents = residents;
    }
}