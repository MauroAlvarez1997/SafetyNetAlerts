package com.openclassrooms.SafetyNetAlerts.mapper;

import com.openclassrooms.SafetyNetAlerts.dto.MedicalRecordDTO;
import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;

/**
 * Mapper class to convert between MedicalRecord entities and MedicalRecordDTO objects.
 */
public class MedicalRecordMapper {

    /**
     * Converts a MedicalRecord entity to a MedicalRecordDTO.
     *
     * @param medicalRecord the MedicalRecord entity to convert
     * @return a MedicalRecordDTO containing the same data
     */
    public static MedicalRecordDTO toDto(MedicalRecord medicalRecord) {
        return new MedicalRecordDTO(
                medicalRecord.getFirstName(),
                medicalRecord.getLastName(),
                medicalRecord.getBirthdate(),
                medicalRecord.getMedications(),
                medicalRecord.getAllergies()
        );
    }

}
