package com.openclassrooms.SafetyNetAlerts.service;

import com.openclassrooms.SafetyNetAlerts.dto.FireStationDTO;
import com.openclassrooms.SafetyNetAlerts.exceptions.FireStationNotFoundException;
import com.openclassrooms.SafetyNetAlerts.model.FireStation;
import com.openclassrooms.SafetyNetAlerts.repository.FireStationRepository;
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
class FireStationServiceTest {

    @Mock
    private FireStationRepository fireStationRepository;

    @InjectMocks
    private FireStationService fireStationService;

    @Test
    void getAllFireStations_shouldReturnMappedDtos() {
        FireStation station = new FireStation();
        station.setAddress("1509 Culver St");
        station.setStation("3");

        when(fireStationRepository.findAll()).thenReturn(List.of(station));

        List<FireStationDTO> result = fireStationService.getAllFireStations();

        assertEquals(1, result.size());
        assertEquals("1509 Culver St", result.get(0).getAddress());
        assertEquals("3", result.get(0).getStation());
    }

    @Test
    void addFireStation_shouldSaveAndReturnDto() {
        FireStation input = new FireStation();
        input.setAddress("29 15th St");
        input.setStation("2");

        FireStationDTO result = fireStationService.addFireStation(input);

        verify(fireStationRepository, times(1)).save(any(FireStation.class));
        assertEquals("29 15th St", result.getAddress());
        assertEquals("2", result.getStation());
    }

    @Test
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

        assertEquals("4", result.getStation());
        verify(fireStationRepository, times(1)).persist();
    }

    @Test
    void updateFireStation_shouldThrowException_whenNotFound() {
        when(fireStationRepository.findByAddress("Unknown"))
                .thenReturn(Optional.empty());

        FireStation update = new FireStation();
        update.setStation("2");

        assertThrows(
                FireStationNotFoundException.class,
                () -> fireStationService.updateFireStation("Unknown", update)
        );

        verify(fireStationRepository, never()).persist();
    }

    @Test
    void deleteFireStation_shouldDeleteExistingStation() {
        FireStation existing = new FireStation();
        existing.setAddress("1509 Culver St");
        existing.setStation("3");

        when(fireStationRepository.findByAddress("1509 Culver St"))
                .thenReturn(Optional.of(existing));

        fireStationService.deleteFireStation("1509 Culver St");

        verify(fireStationRepository, times(1)).delete(existing);
    }

    @Test
    void deleteFireStation_shouldThrowException_whenNotFound() {
        when(fireStationRepository.findByAddress("Unknown"))
                .thenReturn(Optional.empty());

        assertThrows(
                FireStationNotFoundException.class,
                () -> fireStationService.deleteFireStation("Unknown")
        );

        verify(fireStationRepository, never()).delete(any());
    }
}
