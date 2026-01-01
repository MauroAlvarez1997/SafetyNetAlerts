package com.openclassrooms.SafetyNetAlerts.service;

import com.openclassrooms.SafetyNetAlerts.dto.FireStationDTO;
import com.openclassrooms.SafetyNetAlerts.exceptions.FireStationNotFoundException;
import com.openclassrooms.SafetyNetAlerts.mapper.FireStationMapper;
import com.openclassrooms.SafetyNetAlerts.model.FireStation;
import com.openclassrooms.SafetyNetAlerts.repository.FireStationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service layer for managing FireStation objects.
 * Handles business logic and communicates with {@link FireStationRepository}.
 */
@Service
public class FireStationService {

    private static final Logger logger = LoggerFactory.getLogger(FireStationService.class);
    private final FireStationRepository repo;

    /**
     * Constructs a FireStationService with the given repository.
     *
     * @param repo repository for fire station data
     */
    public FireStationService(FireStationRepository repo) {
        this.repo = repo;
    }

    /**
     * Retrieves all fire stations and maps them to DTOs.
     *
     * @return list of {@link FireStationDTO} objects
     */
    public List<FireStationDTO> getAllFireStations() {
        logger.debug("Service: Fetching all fire stations");
        return repo.findAll().stream()
                .map(FireStationMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Adds a new fire station.
     *
     * @param createDto fire station data to create
     * @return the created {@link FireStationDTO}
     */
    public FireStationDTO addFireStation(FireStation createDto) {
        logger.debug("Service: Adding fire station {} {}",
                createDto.getAddress(), createDto.getStation());

        FireStation fireStation = FireStationMapper.fromCreateDto(createDto);
        repo.save(fireStation);
        return FireStationMapper.toDto(fireStation);
    }

    /**
     * Updates an existing fire station by address.
     *
     * @param address   address of the fire station to update
     * @param updateDto new fire station data
     * @return the updated {@link FireStationDTO}
     * @throws FireStationNotFoundException if no fire station exists for the given address
     */
    public FireStationDTO updateFireStation(String address, FireStation updateDto) {

        FireStation existingFireStation = repo.findByAddress(address)
                .orElseThrow(() ->
                        new FireStationNotFoundException("Fire Station not found for address: " + address));
        logger.debug("Service: updating fire station for address {}", address);
        existingFireStation.setStation(updateDto.getStation());
        repo.persist();

        return FireStationMapper.toDto(existingFireStation);
    }

    /**
     * Deletes a fire station by address.
     *
     * @param address address of the fire station to delete
     * @throws FireStationNotFoundException if no fire station exists for the given address
     */
    public void deleteFireStation(String address) {

        FireStation fireStation = repo.findByAddress(address)
                .orElseThrow(() ->
                        new FireStationNotFoundException("Fire Station not found for address: " + address));
        logger.debug("Service: deleting fire station {}", address);
        repo.delete(fireStation);
    }
}
