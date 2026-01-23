package com.openclassrooms.SafetyNetAlerts.dto;

import java.util.List;

public class FloodAddressDTO {

    private String address;
    private List<ResidentFloodDTO> residents;

    public FloodAddressDTO() {}

    public FloodAddressDTO(String address, List<ResidentFloodDTO> residents) {
        this.address = address;
        this.residents = residents;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ResidentFloodDTO> getResidents() {
        return residents;
    }

    public void setResidents(List<ResidentFloodDTO> residents) {
        this.residents = residents;
    }
}
