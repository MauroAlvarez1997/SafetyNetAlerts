package com.openclassrooms.SafetyNetAlerts.service;

import com.openclassrooms.SafetyNetAlerts.dto.FireAddressDTO;
import com.openclassrooms.SafetyNetAlerts.dto.ResidentFireDTO;
import com.openclassrooms.SafetyNetAlerts.model.FireStation;
import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import com.openclassrooms.SafetyNetAlerts.repository.FireStationRepository;
import com.openclassrooms.SafetyNetAlerts.repository.MedicalRecordRepository;
import com.openclassrooms.SafetyNetAlerts.repository.PersonRepository;
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
class FireServiceTest {

    @Mock
    private FireStationRepository fireStationRepository;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @InjectMocks
    private FireService fireService;

    @Test
    void getFireInfoByAddress_shouldReturnStationAndResidents() {

        // ---- Fire station ----
        FireStation fireStation = new FireStation();
        fireStation.setAddress("1509 Culver St");
        fireStation.setStation("3");

        when(fireStationRepository.findByAddress("1509 Culver St"))
                .thenReturn(Optional.of(fireStation));

        // ---- Person ----
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Boyd");
        person.setPhone("841-874-6512");
        person.setAddress("1509 Culver St");

        when(personRepository.findByAddress("1509 Culver St"))
                .thenReturn(List.of(person));

        // ---- Medical record ----
        MedicalRecord record = new MedicalRecord();
        record.setBirthdate("03/06/1984");
        record.setMedications(List.of("aznol:350mg"));
        record.setAllergies(List.of("nillacilan"));

        when(medicalRecordRepository.findByName("John", "Boyd"))
                .thenReturn(Optional.of(record));

        // ---- Execute ----
        FireAddressDTO result =
                fireService.getFireInfoByAddress("1509 Culver St");

        // ---- Verify ----
        assertNotNull(result);
        assertEquals("3", result.getStationNumber());

        List<ResidentFireDTO> residents = result.getResidents();
        assertEquals(1, residents.size());

        ResidentFireDTO resident = residents.get(0);
        assertEquals("John", resident.getFirstName());
        assertEquals("Boyd", resident.getLastName());
        assertEquals("841-874-6512", resident.getPhone());
        assertTrue(resident.getAge() > 0);
        assertEquals(1, resident.getMedications().size());
        assertEquals(1, resident.getAllergies().size());

        verify(fireStationRepository, times(1))
                .findByAddress("1509 Culver St");
        verify(personRepository, times(1))
                .findByAddress("1509 Culver St");
        verify(medicalRecordRepository, times(1))
                .findByName("John", "Boyd");
    }
}
