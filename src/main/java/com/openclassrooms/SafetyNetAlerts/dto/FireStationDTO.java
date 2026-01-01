package com.openclassrooms.SafetyNetAlerts.dto;

/**
 * Data Transfer Object representing a fire station mapping.
 * <p>
 * Contains the address and the associated fire station number.
 */
public class FireStationDTO {
    private String address;
    private String station;

    /**
     * Default constructor.
     */
    public FireStationDTO(){}

    /**
     * Constructs a FireStationDTO with the given address and station number.
     *
     * @param address the address covered by the fire station
     * @param station the fire station number
     */
    public FireStationDTO(String address, String station){
        this.address = address;
        this.station = station;
    }

    /**
     * Returns the address covered by the fire station.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address covered by the fire station.
     *
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the fire station number.
     *
     * @return the fire station number
     */
    public String getStation() {
        return station;
    }

    /**
     * Sets the fire station number.
     *
     * @param station the fire station number to set
     */
    public void setStation(String station) {
        this.station = station;
    }
}
