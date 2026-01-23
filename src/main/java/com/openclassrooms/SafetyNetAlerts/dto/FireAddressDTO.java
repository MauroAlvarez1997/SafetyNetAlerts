package com.openclassrooms.SafetyNetAlerts.dto;

import java.util.List;

public class FireAddressDTO {

    private String stationNumber;
    private List<ResidentFireDTO> residents;

    public FireAddressDTO() {}

    public FireAddressDTO(String stationNumber, List<ResidentFireDTO> residents) {
        this.stationNumber = stationNumber;
        this.residents = residents;
    }

    public String getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(String stationNumber) {
        this.stationNumber = stationNumber;
    }

    public List<ResidentFireDTO> getResidents() {
        return residents;
    }

    public void setResidents(List<ResidentFireDTO> residents) {
        this.residents = residents;
    }
}
