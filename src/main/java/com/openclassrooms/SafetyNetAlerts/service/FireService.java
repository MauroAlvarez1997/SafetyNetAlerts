package com.openclassrooms.SafetyNetAlerts.service;

import com.openclassrooms.SafetyNetAlerts.Utils.AgeUtils;
import com.openclassrooms.SafetyNetAlerts.dto.FireAddressDTO;
import com.openclassrooms.SafetyNetAlerts.dto.ResidentFireDTO;
import com.openclassrooms.SafetyNetAlerts.model.FireStation;
import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import com.openclassrooms.SafetyNetAlerts.repository.FireStationRepository;
import com.openclassrooms.SafetyNetAlerts.repository.MedicalRecordRepository;
import com.openclassrooms.SafetyNetAlerts.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FireService {

    private final FireStationRepository fireStationRepository;
    private final PersonRepository personRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    public FireService(FireStationRepository fireStationRepository,
                       PersonRepository personRepository,
                       MedicalRecordRepository medicalRecordRepository) {
        this.fireStationRepository = fireStationRepository;
        this.personRepository = personRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    /**
     * Returns the fire station number and residents living at a given address.
     *
     * @param address address to look up
     * @return fire response DTO
     */
    public FireAddressDTO getFireInfoByAddress(String address) {

        // 1. Find fire station serving the address
        String stationNumber = fireStationRepository.findByAddress(address)
                .map(FireStation::getStation)
                .orElse(null);

        // 2. Find residents at the address (repository does filtering)
        List<ResidentFireDTO> residents = personRepository.findByAddress(address)
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

                    return new ResidentFireDTO(
                            person.getFirstName(),
                            person.getLastName(),
                            person.getPhone(),
                            age,
                            medications,
                            allergies
                    );
                })
                .toList();

        // 3. Build response
        return new FireAddressDTO(stationNumber, residents);
    }
}

