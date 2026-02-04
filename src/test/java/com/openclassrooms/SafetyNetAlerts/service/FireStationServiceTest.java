package com.openclassrooms.SafetyNetAlerts.service;

import com.openclassrooms.SafetyNetAlerts.dto.FireStationCoverageDTO;
import com.openclassrooms.SafetyNetAlerts.dto.FireStationDTO;
import com.openclassrooms.SafetyNetAlerts.model.FireStation;
import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import com.openclassrooms.SafetyNetAlerts.repository.FireStationRepository;
import com.openclassrooms.SafetyNetAlerts.repository.MedicalRecordRepository;
import com.openclassrooms.SafetyNetAlerts.repository.PersonRepository;
import com.openclassrooms.SafetyNetAlerts.exceptions.FireStationNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("FireStationService Tests")
class FireStationServiceTest {

    @Mock
    private FireStationRepository fireStationRepository;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonLookupService personLookupService;

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @InjectMocks
    private FireStationService fireStationService;

    @Test
    @DisplayName("Should return all fire stations as DTOs")
    void getAllFireStations_shouldReturnMappedDtos() {
        FireStation station = new FireStation();
        station.setAddress("1509 Culver St");
        station.setStation("3");

        when(fireStationRepository.findAll()).thenReturn(List.of(station));

        List<FireStationDTO> result = fireStationService.getAllFireStations();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getAddress()).isEqualTo("1509 Culver St");
    }

    @Test
    @DisplayName("Should save and return new fire station as DTO")
    void addFireStation_shouldSaveAndReturnDto() {
        FireStation input = new FireStation();
        input.setAddress("29 15th St");
        input.setStation("2");

        FireStationDTO result = fireStationService.addFireStation(input);

        verify(fireStationRepository).save(any(FireStation.class));
        assertThat(result.getStation()).isEqualTo("2");
    }

    @Test
    @DisplayName("Should update existing fire station and persist changes")
    void updateFireStation_shouldUpdateStationAndPersist() {
        FireStation existing = new FireStation();
        existing.setAddress("1509 Culver St");
        existing.setStation("3");

        FireStation update = new FireStation();
        update.setStation("4");

        when(fireStationRepository.findByAddress("1509 Culver St"))
                .thenReturn(Optional.of(existing));

        FireStationDTO result =
                fireStationService.updateFireStation("1509 Culver St", update);

        assertThat(result.getStation()).isEqualTo("4");
        verify(fireStationRepository).persist();
    }

    @Test
    @DisplayName("Should throw FireStationNotFoundException when updating non-existent fire station")
    void updateFireStation_shouldThrowException_whenNotFound() {
        when(fireStationRepository.findByAddress("Unknown"))
                .thenReturn(Optional.empty());

        assertThrows(
                FireStationNotFoundException.class,
                () -> fireStationService.updateFireStation("Unknown", new FireStation())
        );
    }

    @Test
    @DisplayName("Should delete existing fire station")
    void deleteFireStation_shouldDeleteExistingStation() {
        FireStation existing = new FireStation();
        existing.setAddress("1509 Culver St");

        when(fireStationRepository.findByAddress("1509 Culver St"))
                .thenReturn(Optional.of(existing));

        fireStationService.deleteFireStation("1509 Culver St");

        verify(fireStationRepository).delete(existing);
    }

    @Test
    @DisplayName("Should return coverage info with residents and child/adult counts for station number")
    void getCoverageByStationNumber_shouldReturnResidentsAndCounts() {
        FireStation fs = new FireStation();
        fs.setAddress("1509 Culver St");
        fs.setStation("3");

        when(fireStationRepository.findByStation("3"))
                .thenReturn(List.of(fs));

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Boyd");
        person.setAddress("1509 Culver St");

        MedicalRecord record = new MedicalRecord();
        record.setBirthdate("01/01/2010"); // child

        PersonMedicalInfo info =
                new PersonMedicalInfo(person, 14, record);

        when(personLookupService.findByAddresses(List.of("1509 Culver St")))
                .thenReturn(List.of(info));

        FireStationCoverageDTO result =
                fireStationService.getCoverageByStationNumber("3");

        assertEquals(1, result.getResidents().size());
        assertEquals(0, result.getAdultCount());
        assertEquals(1, result.getChildCount());
    }

    @Test
    @DisplayName("Should return distinct phone numbers for residents covered by station number")
    void getPhoneNumbersByStation_shouldReturnDistinctPhones() {
        FireStation fs = new FireStation();
        fs.setAddress("1509 Culver St");
        fs.setStation("3");

        when(fireStationRepository.findByStation("3"))
                .thenReturn(List.of(fs));

        Person p1 = new Person();
        p1.setAddress("1509 Culver St");
        p1.setPhone("123");

        Person p2 = new Person();
        p2.setAddress("1509 Culver St");
        p2.setPhone("123"); // duplicate

        when(personRepository.findAll())
                .thenReturn(List.of(p1, p2));

        List<String> phones =
                fireStationService.getPhoneNumbersByStation("3");

        assertEquals(1, phones.size());
        assertEquals("123", phones.get(0));
    }
}
