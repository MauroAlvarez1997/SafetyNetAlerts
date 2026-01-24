package com.openclassrooms.SafetyNetAlerts.service;

import com.openclassrooms.SafetyNetAlerts.dto.PersonInfoDTO;
import com.openclassrooms.SafetyNetAlerts.mapper.PersonInfoMapper;
import com.openclassrooms.SafetyNetAlerts.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PersonInfoService {

    private final PersonRepository personRepository;
    private final PersonLookupService lookupService;

    public PersonInfoService(PersonRepository personRepository,
                             PersonLookupService lookupService) {
        this.personRepository = personRepository;
        this.lookupService = lookupService;
    }

    public List<PersonInfoDTO> getPersonsByLastName(String lastName) {

        return personRepository.findAll().stream()
                .filter(p -> p.getLastName().equalsIgnoreCase(lastName))
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
