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
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FloodService {

    private final FireStationRepository fireStationRepository;
    private final PersonRepository personRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    public FloodService(FireStationRepository fireStationRepository,
                        PersonRepository personRepository,
                        MedicalRecordRepository medicalRecordRepository) {
        this.fireStationRepository = fireStationRepository;
        this.personRepository = personRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    /**
     * Returns households grouped by address for the given fire stations.
     *
     * @param stations list of station numbers
     * @return list of flood address DTOs
     */
    public List<FloodAddressDTO> getHouseholdsByStations(List<String> stations) {

        // 1. Find all addresses covered by the given stations
        Set<String> addresses = fireStationRepository.findAll().stream()
                .filter(fs -> stations.contains(fs.getStation()))
                .map(FireStation::getAddress)
                .collect(HashSet::new, HashSet::add, HashSet::addAll);

        // 2. Group residents by address
        Map<String, List<ResidentFloodDTO>> households = new HashMap<>();

        for (String address : addresses) {

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
        return households.entrySet().stream()
                .map(entry -> new FloodAddressDTO(entry.getKey(), entry.getValue()))
                .toList();
    }
}
