package com.openclassrooms.SafetyNetAlerts.service;

import com.openclassrooms.SafetyNetAlerts.Utils.AgeUtils;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import com.openclassrooms.SafetyNetAlerts.repository.MedicalRecordRepository;
import com.openclassrooms.SafetyNetAlerts.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Service for retrieving detailed information about persons, including
 * their associated medical records and calculated age.
 *
 * This service acts as a helper for other services like ChildAlertService
 * or FireStationService, providing combined person + medical record data.
 */
@Service
public class PersonLookupService {
    private static final Logger logger = LoggerFactory.getLogger(PersonLookupService.class);

    private final PersonRepository personRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    /**
     * Constructs a PersonLookupService with the given repositories.
     *
     * @param personRepository repository for retrieving person data
     * @param medicalRecordRepository repository for retrieving medical record data
     */
    public PersonLookupService(PersonRepository personRepository,
                               MedicalRecordRepository medicalRecordRepository) {
        this.personRepository = personRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    /**
     * Finds all persons living at the given address and maps them to
     * {@link PersonMedicalInfo} objects that include person details,
     * medical record data, and age.
     *
     * @param address the address to search for residents
     * @return a list of PersonMedicalInfo objects for residents at the address
     */
    public List<PersonMedicalInfo> findByAddress(String address) {
        logger.debug("Service: Finding all people in an address");
        return personRepository.findByAddress(address).stream()
                .map(this::toPersonMedicalInfo)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Converts a {@link Person} and their corresponding {@link com.openclassrooms.SafetyNetAlerts.model.MedicalRecord}
     * into a {@link PersonMedicalInfo} object. Returns null if no medical record is found.
     *
     * @param person the person to convert
     * @return a PersonMedicalInfo object including person, age, and medical record, or null if medical record is missing
     */
    private PersonMedicalInfo toPersonMedicalInfo(Person person) {
        logger.debug("Service: Converting to PersonMedicalInfo object");
        return medicalRecordRepository
                .findByName(person.getFirstName(), person.getLastName())
                .map(record ->
                        new PersonMedicalInfo(
                                person,
                                AgeUtils.calculateAge(record.getBirthdate()),
                                record))
                .orElse(null);
    }

    /**
     * Retrieves all persons with medical information for the given list of addresses.
     *
     * @param addresses list of addresses to search
     * @return list of persons with their associated medical records and age
     */
    public List<PersonMedicalInfo> findByAddresses(List<String> addresses) {
        return addresses.stream()
                .flatMap(address -> findByAddress(address).stream())
                .toList();
    }

}

