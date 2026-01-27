package com.openclassrooms.SafetyNetAlerts.service;

import com.openclassrooms.SafetyNetAlerts.dto.FloodAddressDTO;
import com.openclassrooms.SafetyNetAlerts.dto.ResidentFloodDTO;
import com.openclassrooms.SafetyNetAlerts.model.FireStation;
import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import com.openclassrooms.SafetyNetAlerts.repository.FireStationRepository;
import com.openclassrooms.SafetyNetAlerts.repository.MedicalRecordRepository;
import com.openclassrooms.SafetyNetAlerts.repository.PersonRepository;
import com.openclassrooms.SafetyNetAlerts.Utils.AgeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service responsible for organizing emergency data for flood-prone areas.
 * It groups residents by their household address for all areas covered by
 * specific fire stations.
 */
@Service
public class FloodService {

    private static final Logger logger = LoggerFactory.getLogger(FloodService.class);
    private final FireStationRepository fireStationRepository;
    private final PersonRepository personRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    /**
     * Constructs the FloodService with required repositories for stations,
     * persons, and medical records.
     *
     * @param fireStationRepository the repository to look up station-to-address mapping
     * @param personRepository the repository to find residents at specific addresses
     * @param medicalRecordRepository the repository to retrieve medical history for residents
     */
    public FloodService(FireStationRepository fireStationRepository,
                        PersonRepository personRepository,
                        MedicalRecordRepository medicalRecordRepository) {
        this.fireStationRepository = fireStationRepository;
        this.personRepository = personRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    /**
     * Returns a list of households grouped by address for the provided fire station numbers.
     * Each household includes detailed resident information including age and medical history.
     *
     * @param stations a list of station numbers to filter by
     * @return a list of FloodAddressDTOs containing addresses and their respective residents
     */
    public List<FloodAddressDTO> getHouseholdsByStations(List<String> stations) {
        logger.debug("Service: processing flood data for station numbers: {}", stations);

        // 1. Find all addresses covered by the given stations
        Set<String> addresses = fireStationRepository.findAll().stream()
                .filter(fs -> stations.contains(fs.getStation()))
                .map(FireStation::getAddress)
                .collect(HashSet::new, HashSet::add, HashSet::addAll);

        logger.debug("Service: found {} unique addresses covered by these stations", addresses.size());

        // 2. Group residents by address
        Map<String, List<ResidentFloodDTO>> households = new HashMap<>();

        for (String address : addresses) {
            logger.debug("Service: mapping residents for address: {}", address);

            List<ResidentFloodDTO> residents = personRepository.findByAddress(address)
                    .stream()
                    .map(person -> {
                        MedicalRecord record = medicalRecordRepository
                                .findByName(person.getFirstName(), person.getLastName())
                                .orElse(null);

                        int age = 0;
                        List<String> medications = List.of();
                        List<String> allergies = List.of();

                        if (record != null) {
                            age = AgeUtils.calculateAge(record.getBirthdate());
                            medications = record.getMedications();
                            allergies = record.getAllergies();
                        }

                        return new ResidentFloodDTO(
                                person.getFirstName(),
                                person.getLastName(),
                                person.getPhone(),
                                age,
                                medications,
                                allergies
                        );
                    })
                    .toList();

            households.put(address, residents);
        }

        // 3. Build response grouped by address
        logger.debug("Service: successfully grouped {} households", households.size());
        return households.entrySet().stream()
                .map(entry -> new FloodAddressDTO(entry.getKey(), entry.getValue()))
                .toList();
    }
}