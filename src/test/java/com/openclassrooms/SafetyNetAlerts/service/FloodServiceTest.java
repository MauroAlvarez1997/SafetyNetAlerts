package com.openclassrooms.SafetyNetAlerts.service;

import com.openclassrooms.SafetyNetAlerts.dto.FloodAddressDTO;
import com.openclassrooms.SafetyNetAlerts.dto.ResidentFloodDTO;
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
class FloodServiceTest {

    @Mock
    private FireStationRepository fireStationRepository;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @InjectMocks
    private FloodService floodService;

    @Test
    void getHouseholdsByStations_shouldGroupResidentsByAddress() {

        // ---- Fire stations ----
        FireStation fs1 = new FireStation();
        fs1.setAddress("1509 Culver St");
        fs1.setStation("1");

        when(fireStationRepository.findAll())
                .thenReturn(List.of(fs1));

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
        List<FloodAddressDTO> result =
                floodService.getHouseholdsByStations(List.of("1"));

        // ---- Verify ----
        assertEquals(1, result.size());

        FloodAddressDTO addressDTO = result.get(0);
        assertEquals("1509 Culver St", addressDTO.getAddress());

        List<ResidentFloodDTO> residents = addressDTO.getResidents();
        assertEquals(1, residents.size());

        ResidentFloodDTO resident = residents.get(0);
        assertEquals("John", resident.getFirstName());
        assertEquals("Boyd", resident.getLastName());
        assertEquals("841-874-6512", resident.getPhone());
        assertTrue(resident.getAge() > 0);
        assertEquals(1, resident.getMedications().size());
        assertEquals(1, resident.getAllergies().size());

        verify(fireStationRepository, times(1)).findAll();
        verify(personRepository, times(1)).findByAddress("1509 Culver St");
        verify(medicalRecordRepository, times(1))
                .findByName("John", "Boyd");
    }
}
