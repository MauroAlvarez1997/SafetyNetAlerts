package com.openclassrooms.SafetyNetAlerts.mapper;

import com.openclassrooms.SafetyNetAlerts.dto.PersonInfoDTO;
import com.openclassrooms.SafetyNetAlerts.service.PersonMedicalInfo;

public class PersonInfoMapper {

    public static PersonInfoDTO toDto(PersonMedicalInfo info) {
        return new PersonInfoDTO(
                info.getPerson().getFirstName(),
                info.getPerson().getLastName(),
                info.getPerson().getAddress(),
                info.getAge(),
                info.getPerson().getEmail(),
                info.getMedicalRecord().getMedications(),
                info.getMedicalRecord().getAllergies()
        );
    }
}
