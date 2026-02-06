package com.openclassrooms.SafetyNetAlerts.service;

import com.openclassrooms.SafetyNetAlerts.dto.FireStationCoverageDTO;
import com.openclassrooms.SafetyNetAlerts.dto.FireStationDTO;
import com.openclassrooms.SafetyNetAlerts.dto.ResidentDTO;
import com.openclassrooms.SafetyNetAlerts.exceptions.FireStationNotFoundException;
import com.openclassrooms.SafetyNetAlerts.mapper.FireStationMapper;
import com.openclassrooms.SafetyNetAlerts.mapper.ResidentMapper;
import com.openclassrooms.SafetyNetAlerts.model.FireStation;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import com.openclassrooms.SafetyNetAlerts.repository.FireStationRepository;
import com.openclassrooms.SafetyNetAlerts.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer for managing FireStation objects.
 * Handles business logic and communicates with {@link FireStationRepository}.
 */
@Service
public class FireStationService {

    private static final Logger logger = LoggerFactory.getLogger(FireStationService.class);
    private final FireStationRepository repo;
    private final PersonRepository personRepository;
    private final PersonLookupService personLookupService;

    /**
     * Constructs a FireStationService with the given repository.
     *
     * @param repo repository for fire station data
     * @param personRepository repository for person data
     * @param personLookupService service for looking up persons with medical info
     */
    public FireStationService(FireStationRepository repo, PersonRepository personRepository, PersonLookupService personLookupService) {
        this.repo = repo;
        this.personRepository = personRepository;
        this.personLookupService = personLookupService;
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
                .toList();
    }

    /**
     * Adds a new fire station.
     *
     * @param fireStation fire station data to create
     * @return the created {@link FireStationDTO}
     */
    public FireStationDTO addFireStation(FireStation fireStation) {
        logger.debug("Service: Adding fire station {} {}",
                fireStation.getAddress(), fireStation.getStation());
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

    /**
     * Retrieves all residents covered by a given fire station.
     * The result includes the list of residents living in the coverage area
     * along with the number of adults and children served by the station.
     *
     * @param stationNumber fire station number used to identify the coverage area
     * @return fire station coverage data including residents and population counts
     */
    public FireStationCoverageDTO getCoverageByStationNumber(String stationNumber) {

        // 1. Get addresses covered by the station
        List<String> addresses = repo.findByStation(stationNumber).stream()
                .map(FireStation::getAddress)
                .toList();

        // 2. Get persons + medical records in one pass
        List<PersonMedicalInfo> people =
                personLookupService.findByAddresses(addresses);

        // 3. Map to ResidentDTO
        List<ResidentDTO> residents = people.stream()
                .map(p -> ResidentMapper.toDto(p.getPerson()))
                .toList();

        // 4. Count adults & children
        long childCount = people.stream()
                .filter(p -> p.getAge() <= 18)
                .count();

        long adultCount = people.size() - childCount;

        // 5. Return DTO
        return new FireStationCoverageDTO(
                residents,
                (int) adultCount,
                (int) childCount
        );
    }

    /**
     * Retrieves all unique phone numbers of residents served by the specified fire station.
     * This list is used for emergency alerting purposes.
     *
     * @param stationNumber fire station number used to identify the coverage area
     * @return list of distinct phone numbers of residents covered by the fire station
     */
    public List<String> getPhoneNumbersByStation(String stationNumber) {

        // 1. Get addresses covered by the station
        List<String> addresses = repo.findByStation(stationNumber)
                .stream()
                .map(FireStation::getAddress)
                .toList();

        // 2. Get persons at those addresses
        return personRepository.findAll().stream()
                .filter(person -> addresses.contains(person.getAddress()))
                .map(Person::getPhone)
                .distinct()   // IMPORTANT
                .toList();
    }


}
