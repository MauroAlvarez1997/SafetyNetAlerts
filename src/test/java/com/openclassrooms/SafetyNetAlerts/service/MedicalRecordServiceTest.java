package com.openclassrooms.SafetyNetAlerts.service;

import com.openclassrooms.SafetyNetAlerts.dto.MedicalRecordDTO;
import com.openclassrooms.SafetyNetAlerts.exceptions.MedicalRecordNotFoundException;
import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;
import com.openclassrooms.SafetyNetAlerts.repository.MedicalRecordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedicalRecordServiceTest {

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @InjectMocks
    private MedicalRecordService medicalRecordService;

    @Test
    void getAllMedicalRecords_shouldReturnMappedDtos() {
        MedicalRecord record = new MedicalRecord();
        record.setFirstName("John");
        record.setLastName("Boyd");
        record.setBirthdate("03/06/1984");
        record.setMedications(List.of("aznol:350mg"));

        when(medicalRecordRepository.findAll()).thenReturn(List.of(record));

        List<MedicalRecordDTO> result = medicalRecordService.getAllMedicalRecords();

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals("03/06/1984", result.get(0).getBirthdate());
        assertTrue(result.get(0).getMedications().contains("aznol:350mg"));
        verify(medicalRecordRepository, times(1)).findAll();
    }

    @Test
    void addMedicalRecord_shouldSaveAndReturnDto() {
        MedicalRecord newRecord = new MedicalRecord();
        newRecord.setFirstName("Jane");
        newRecord.setLastName("Doe");
        newRecord.setBirthdate("01/01/2000");

        MedicalRecordDTO result = medicalRecordService.addMedicalRecord(newRecord);

        verify(medicalRecordRepository, times(1)).save(newRecord);

        assertNotNull(result);
        assertEquals("Jane", result.getFirstName());
        assertEquals("Doe", result.getLastName());
    }

    @Test
    void updateMedicalRecord_shouldUpdateMedicalFieldsAndPersist() {
        MedicalRecord existing = new MedicalRecord();
        existing.setFirstName("John");
        existing.setLastName("Boyd");
        existing.setBirthdate("01/01/1900");
        existing.setMedications(List.of("old:100mg"));

        MedicalRecord updateData = new MedicalRecord();
        updateData.setBirthdate("03/06/1984");
        updateData.setMedications(List.of("aznol:350mg"));
        updateData.setAllergies(List.of("nillacilan"));

        when(medicalRecordRepository.findByName("John", "Boyd"))
                .thenReturn(Optional.of(existing));

        MedicalRecordDTO result = medicalRecordService.updateMedicalRecord("John", "Boyd", updateData);

        assertEquals("03/06/1984", result.getBirthdate());
        assertTrue(result.getMedications().contains("aznol:350mg"));
        verify(medicalRecordRepository, times(1)).persist();
    }

    @Test
    void delete_shouldThrowException_whenNotFound() {
        when(medicalRecordRepository.findByName("None", "Exist"))
                .thenReturn(Optional.empty());

        assertThrows(MedicalRecordNotFoundException.class,
                () -> medicalRecordService.deleteMedicalRecord("None", "Exist"));
    }
}