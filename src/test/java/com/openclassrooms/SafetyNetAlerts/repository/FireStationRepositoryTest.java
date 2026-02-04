package com.openclassrooms.SafetyNetAlerts.repository;

import com.openclassrooms.SafetyNetAlerts.model.FireStation;
import com.openclassrooms.SafetyNetAlerts.model.JsonData;
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
@DisplayName("FireStationRepository Tests")
class FireStationRepositoryTest {

    @Mock
    private JsonDataRepository jsonDataRepository;

    @InjectMocks
    private FireStationRepository fireStationRepository;

    private JsonData jsonData;
    private List<FireStation> fireStations;

    @BeforeEach
    void setUp() {
        fireStations = new ArrayList<>();
        jsonData = new JsonData();
        jsonData.setFireStations(fireStations);
    }

    @Test
    @DisplayName("Should return all fire stations from repository")
    void findAll_shouldReturnAllFireStations() {
        when(jsonDataRepository.getData()).thenReturn(jsonData);

        FireStation station = new FireStation();
        station.setAddress("1509 Culver St");
        station.setStation("3");

        fireStations.add(station);

        List<FireStation> result = fireStationRepository.findAll();

        assertEquals(1, result.size());
        assertEquals("1509 Culver St", result.get(0).getAddress());
    }

    @Test
    @DisplayName("Should return fire station when found by address (case-insensitive)")
    void findByAddress_shouldReturnMatchingFireStation_whenFound() {
        when(jsonDataRepository.getData()).thenReturn(jsonData);

        FireStation station = new FireStation();
        station.setAddress("1509 Culver St");
        station.setStation("3");

        fireStations.add(station);

        Optional<FireStation> result =
                fireStationRepository.findByAddress("1509 culver st");

        assertTrue(result.isPresent());
        assertEquals("3", result.get().getStation());
    }

    @Test
    @DisplayName("Should return empty Optional when fire station not found by address")
    void findByAddress_shouldReturnEmpty_whenNotFound() {
        when(jsonDataRepository.getData()).thenReturn(jsonData);

        Optional<FireStation> result =
                fireStationRepository.findByAddress("Unknown Address");

        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should save fire station and persist to data source")
    void save_shouldAddFireStationAndPersist() {
        when(jsonDataRepository.getData()).thenReturn(jsonData);

        FireStation station = new FireStation();
        station.setAddress("29 15th St");
        station.setStation("2");

        fireStationRepository.save(station);

        assertEquals(1, fireStations.size());
        verify(jsonDataRepository, times(1)).persist();
    }

    @Test
    @DisplayName("Should delete fire station and persist changes")
    void delete_shouldRemoveFireStationAndPersist() {
        when(jsonDataRepository.getData()).thenReturn(jsonData);

        FireStation station = new FireStation();
        station.setAddress("29 15th St");
        station.setStation("2");

        fireStations.add(station);

        fireStationRepository.delete(station);

        assertTrue(fireStations.isEmpty());
        verify(jsonDataRepository, times(1)).persist();
    }

    @Test
    @DisplayName("Should delegate persist call to JsonDataRepository")
    void persist_shouldCallJsonRepositoryPersist() {
        fireStationRepository.persist();

        verify(jsonDataRepository, times(1)).persist();
    }
}
