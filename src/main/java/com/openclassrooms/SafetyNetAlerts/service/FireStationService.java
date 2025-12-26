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

@Service
public class FireStationService {

    private static final Logger logger = LoggerFactory.getLogger(FireStationService.class);
    private final FireStationRepository repo;

    public FireStationService(FireStationRepository repo) {
        this.repo = repo;
    }

    public List<FireStationDTO> getAllFireStations() {
        logger.debug("Service: Fetching all fire stations");
        return repo.findAll().stream()
                .map(FireStationMapper::toDto)
                .collect(Collectors.toList());
    }

    public FireStationDTO addFireStation(FireStation createDto) {
        logger.debug("Service: Adding fire station {} {}",
                createDto.getAddress(), createDto.getStation());

        FireStation fireStation = FireStationMapper.fromCreateDto(createDto);
        repo.save(fireStation);
        return FireStationMapper.toDto(fireStation);
    }

    public FireStationDTO updateFireStation(String address, FireStation updateDto) {

        FireStation existingFireStation = repo.findByAddress(address)
                .orElseThrow(() ->
                        new FireStationNotFoundException("Fire Station not found for address: " + address));
        logger.debug("Service: updating fire station for address {}", address);
        existingFireStation.setStation(updateDto.getStation());
        repo.persist();

        return FireStationMapper.toDto(existingFireStation);
    }

    public void deleteFireStation(String address) {

        FireStation fireStation = repo.findByAddress(address)
                .orElseThrow(() ->
                        new FireStationNotFoundException("Fire Station not found for address: " + address));
        logger.debug("Service: deleting fire station {}", address);
        repo.delete(fireStation);
    }
}

