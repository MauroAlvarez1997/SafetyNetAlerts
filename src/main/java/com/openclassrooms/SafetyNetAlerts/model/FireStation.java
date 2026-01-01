package com.openclassrooms.SafetyNetAlerts.model;

/**
 * Represents a FireStation entity.
 */
public class FireStation {

    private String address;
    private String station;

    /**
     * Default constructor.
     */
    public FireStation() {}

    /**
     * Constructs a new FireStation with the specified address and station number.
     *
     * @param address the address of the fire station
     * @param station the station number
     */
    public FireStation(String address, String station) {
        this.address = address;
        this.station = station;
    }

    /**
     * Gets the address of the fire station.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the fire station.
     *
     * @param address the new address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the station number.
     *
     * @return the station number
     */
    public String getStation() {
        return station;
    }

    /**
     * Sets the station number.
     *
     * @param station the new station number
     */
    public void setStation(String station) {
        this.station = station;
    }
}
