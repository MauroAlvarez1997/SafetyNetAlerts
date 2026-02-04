package com.openclassrooms.SafetyNetAlerts.repository;

import com.openclassrooms.SafetyNetAlerts.model.JsonData;
import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("MedicalRecordRepository Tests")
class MedicalRecordRepositoryTest {

    @Mock
    private JsonDataRepository jsonDataRepository;

    @InjectMocks
    private MedicalRecordRepository medicalRecordRepository;

    private JsonData jsonData;
    private List<MedicalRecord> medicalRecords;

    @BeforeEach
    void setUp() {
        medicalRecords = new ArrayList<>();
        jsonData = new JsonData();
        jsonData.setMedicalRecords(medicalRecords);
    }

    @Test
    @DisplayName("Should return all medical records from repository")
    void findAll_shouldReturnAllMedicalRecords() {
        when(jsonDataRepository.getData()).thenReturn(jsonData);
        MedicalRecord record = new MedicalRecord();
        record.setFirstName("John");
        record.setLastName("Boyd");
        medicalRecords.add(record);

        List<MedicalRecord> result = medicalRecordRepository.findAll();

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
    }

    @Test
    @DisplayName("Should return medical record when found by name (case-insensitive)")
    void findByName_shouldReturnMatchingRecord_whenFound() {
        when(jsonDataRepository.getData()).thenReturn(jsonData);
        MedicalRecord record = new MedicalRecord();
        record.setFirstName("Jacob");
        record.setLastName("Boyd");
        medicalRecords.add(record);

        Optional<MedicalRecord> result = medicalRecordRepository.findByName("jacob", "boyd");

        assertTrue(result.isPresent());
        assertEquals("Jacob", result.get().getFirstName());
    }

    @Test
    @DisplayName("Should save medical record and persist to data source")
    void save_shouldAddRecordAndPersist() {
        when(jsonDataRepository.getData()).thenReturn(jsonData);
        MedicalRecord record = new MedicalRecord();
        record.setFirstName("New");
        record.setLastName("Patient");

        medicalRecordRepository.save(record);

        assertEquals(1, medicalRecords.size());
        verify(jsonDataRepository, times(1)).persist();
    }

    @Test
    @DisplayName("Should delete medical record and persist changes")
    void delete_shouldRemoveRecordAndPersist() {
        when(jsonDataRepository.getData()).thenReturn(jsonData);

        MedicalRecord record = new MedicalRecord();
        record.setFirstName("John");
        record.setLastName("Boyd");
        medicalRecords.add(record);

        assertEquals(1, medicalRecords.size());

        medicalRecordRepository.delete(record);

        assertTrue(medicalRecords.isEmpty(), "The medical records list should be empty after deletion");
        verify(jsonDataRepository, times(1)).persist();
    }
}