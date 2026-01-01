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

    /**
     * Converts a MedicalRecord object (currently passed as a MedicalRecord entity) to a new MedicalRecord entity.
     *
     * @param dto the MedicalRecord object containing data (ideally should be a DTO)
     * @return a new MedicalRecord entity with the same data
     */
    public static MedicalRecord fromCreateDto(MedicalRecord dto){
      MedicalRecord medicalRecord = new MedicalRecord();
      medicalRecord.setFirstName(dto.getFirstName());
      medicalRecord.setLastName(dto.getLastName());
      medicalRecord.setBirthdate(dto.getBirthdate());
      medicalRecord.setMedications(dto.getMedications());
      medicalRecord.setAllergies(dto.getAllergies());
      return medicalRecord;
    };

}
