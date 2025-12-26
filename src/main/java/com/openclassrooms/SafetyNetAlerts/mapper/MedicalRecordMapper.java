package com.openclassrooms.SafetyNetAlerts.mapper;

import com.openclassrooms.SafetyNetAlerts.dto.MedicalRecordDTO;
import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;

public class MedicalRecordMapper {

    public static MedicalRecordDTO toDto(MedicalRecord medicalRecord){
        return new MedicalRecordDTO(
                medicalRecord.getFirstName(),
                medicalRecord.getLastName(),
                medicalRecord.getBirthdate(),
                medicalRecord.getMedications(),
                medicalRecord.getAllergies()
        );
    }

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
