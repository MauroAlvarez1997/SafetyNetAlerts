package com.openclassrooms.SafetyNetAlerts.mapper;

import com.openclassrooms.SafetyNetAlerts.dto.PersonInfoDTO;
import com.openclassrooms.SafetyNetAlerts.service.PersonMedicalInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility mapper class responsible for converting PersonMedicalInfo domain models
 * into PersonInfoDTOs. This centralizes the transformation logic for the
 * personInfo endpoint.
 */
public class PersonInfoMapper {

    private static final Logger logger = LoggerFactory.getLogger(PersonInfoMapper.class);

    /**
     * Maps a PersonMedicalInfo object to a PersonInfoDTO.
     * Extracts identity from the Person entity and medical data from the MedicalRecord.
     *
     * @param info the comprehensive PersonMedicalInfo object to be converted
     * @return a populated PersonInfoDTO, or null if the input is null
     */
    public static PersonInfoDTO toDto(PersonMedicalInfo info) {
        if (info == null) {
            logger.warn("Mapper: Attempted to map a null PersonMedicalInfo object");
            return null;
        }

        logger.debug("Mapper: Converting data for {} {}",
                info.getPerson().getFirstName(),
                info.getPerson().getLastName());

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