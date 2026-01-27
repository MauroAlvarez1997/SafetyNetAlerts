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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class that handles business logic for fire-related emergency data.
 * It coordinates data from persons, fire stations, and medical records to
 * provide a comprehensive view of safety information for a given address.
 */
@Service
public class FireService {

    private static final Logger logger = LoggerFactory.getLogger(FireService.class);
    private final FireStationRepository fireStationRepository;
    private final PersonRepository personRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    /**
     * Constructs the FireService with necessary data repositories.
     *
     * @param fireStationRepository repository for fire station mappings
     * @param personRepository repository for resident personal data
     * @param medicalRecordRepository repository for resident medical history
     */
    public FireService(FireStationRepository fireStationRepository,
                       PersonRepository personRepository,
                       MedicalRecordRepository medicalRecordRepository) {
        this.fireStationRepository = fireStationRepository;
        this.personRepository = personRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    /**
     * Retrieves a detailed report of a specific address, including the serving
     * fire station number and a list of all residents with their medical details.
     *
     * @param address the street address to search for
     * @return a FireAddressDTO containing the station number and list of residents
     */
    public FireAddressDTO getFireInfoByAddress(String address) {
        logger.debug("Service: fetching fire info and residents for address: {}", address);

        // 1. Find fire station serving the address
        String stationNumber = fireStationRepository.findByAddress(address)
                .map(FireStation::getStation)
                .orElse(null);

        // 2. Find residents at the address (repository does filtering)
        List<ResidentFireDTO> residents = personRepository.findByAddress(address)
                .stream()
                .map(person -> {
                    logger.debug("Service: processing medical data for resident: {} {}",
                            person.getFirstName(), person.getLastName());

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