package com.openclassrooms.SafetyNetAlerts.service;

import com.openclassrooms.SafetyNetAlerts.model.Person;
import com.openclassrooms.SafetyNetAlerts.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service responsible for community-level queries,
 * such as retrieving email addresses for all residents
 * living in a given city.
 */
@Service
public class CommunityService {

    private final PersonRepository personRepository;

    /**
     * Constructs a CommunityService with the given PersonRepository.
     *
     * @param personRepository repository used to retrieve person data
     */
    public CommunityService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Retrieves all email addresses of residents living in the given city.
     * Duplicate email addresses are removed from the result.
     *
     * @param city city used to search for residents
     * @return list of distinct email addresses
     */
    public List<String> getEmailsByCity(String city) {

        return personRepository.findAll().stream()
                .filter(person -> person.getCity().equalsIgnoreCase(city))
                .map(Person::getEmail)
                .filter(email -> email != null && !email.isBlank())
                .distinct()
                .toList();
    }
}
