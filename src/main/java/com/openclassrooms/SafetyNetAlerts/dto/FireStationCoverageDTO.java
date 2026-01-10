package com.openclassrooms.SafetyNetAlerts.dto;

import java.util.List;

/**
 * Data Transfer Object representing coverage information for a fire station.
 * <p>
 * Contains the list of residents served by the station, along with counts of
 * adults and children in the covered area.
 */
public class FireStationCoverageDTO {

    private List<ResidentDTO> residents;
    private int adultCount;
    private int childCount;

    /**
     * Default constructor.
     */
    public FireStationCoverageDTO() {}

    /**
     * Constructs a {@link FireStationCoverageDTO} with the given residents
     * and counts of adults and children.
     *
     * @param residents the list of residents served by the fire station
     * @param adultCount the number of adults in the covered area
     * @param childCount the number of children (aged 18 or younger) in the covered area
     */
    public FireStationCoverageDTO(List<ResidentDTO> residents, int adultCount, int childCount) {
        this.residents = residents;
        this.adultCount = adultCount;
        this.childCount = childCount;
    }

    /**
     * Returns the list of residents served by the fire station.
     *
     * @return the list of residents
     */
    public List<ResidentDTO> getResidents() {
        return residents;
    }

    /**
     * Sets the list of residents served by the fire station.
     *
     * @param residents the list of residents to set
     */
    public void setResidents(List<ResidentDTO> residents) {
        this.residents = residents;
    }

    /**
     * Returns the number of adults in the covered area.
     *
     * @return the adult count
     */
    public int getAdultCount() {
        return adultCount;
    }

    /**
     * Sets the number of adults in the covered area.
     *
     * @param adultCount the adult count to set
     */
    public void setAdultCount(int adultCount) {
        this.adultCount = adultCount;
    }

    /**
     * Returns the number of children (aged 18 or younger) in the covered area.
     *
     * @return the child count
     */
    public int getChildCount() {
        return childCount;
    }

    /**
     * Sets the number of children (aged 18 or younger) in the covered area.
     *
     * @param childCount the child count to set
     */
    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }
}
