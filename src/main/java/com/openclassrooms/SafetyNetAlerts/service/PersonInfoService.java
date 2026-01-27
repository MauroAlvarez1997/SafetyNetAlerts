package com.openclassrooms.SafetyNetAlerts.service;

import com.openclassrooms.SafetyNetAlerts.dto.PersonInfoDTO;
import com.openclassrooms.SafetyNetAlerts.mapper.PersonInfoMapper;
import com.openclassrooms.SafetyNetAlerts.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Service responsible for aggregating detailed personal information.
 * It combines identity data with medical and address information to provide
 * a comprehensive view of a resident.
 */
@Service
public class PersonInfoService {

    private static final Logger logger = LoggerFactory.getLogger(PersonInfoService.class);
    private final PersonRepository personRepository;
    private final PersonLookupService lookupService;

    /**
     * Constructs a PersonInfoService with the necessary repository and lookup service.
     *
     * @param personRepository the repository to retrieve all person entities
     * @param lookupService the service used to find specific details linked to a person's address
     */
    public PersonInfoService(PersonRepository personRepository,
                             PersonLookupService lookupService) {
        this.personRepository = personRepository;
        this.lookupService = lookupService;
    }

    /**
     * Retrieves a list of detailed information for all residents sharing a specific last name.
     * This method filters all residents, performs a detailed lookup, and maps the results to DTOs.
     *
     * @param lastName the last name to filter residents by
     * @return a list of PersonInfoDTOs containing identity and medical details
     */
    public List<PersonInfoDTO> getPersonsByLastName(String lastName) {
        logger.debug("Service: processing personInfo request for lastName: {}", lastName);

        return personRepository.findAll().stream()
                .filter(p -> p.getLastName().equalsIgnoreCase(lastName))
                .peek(p -> logger.debug("Service: found resident match: {} {}", p.getFirstName(), p.getLastName()))
                .map(p -> lookupService
                        .findByAddress(p.getAddress()).stream()
                        .filter(info ->
                                info.getPerson().getFirstName().equals(p.getFirstName()) &&
                                        info.getPerson().getLastName().equals(p.getLastName()))
                        .findFirst()
                        .orElse(null))
                .filter(Objects::nonNull)
                .map(PersonInfoMapper::toDto)
                .collect(Collectors.toList());
    }
}