package com.openclassrooms.SafetyNetAlerts.repository;

import com.openclassrooms.SafetyNetAlerts.model.Person;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository class for managing Person objects.
 * Interacts with the underlying JSON data store via {@link JsonDataRepository}.
 */
@Repository
public class PersonRepository {

    private final JsonDataRepository jsonRepo;

    /**
     * Constructs a PersonRepository using the given JSON data repository.
     *
     * @param jsonRepo repository that provides access to JSON data
     */
    public PersonRepository(JsonDataRepository jsonRepo) {
        this.jsonRepo = jsonRepo;
    }

    /**
     * Retrieves all persons.
     *
     * @return list of all {@link Person} objects
     */
    public List<Person> findAll() {
        return jsonRepo.getData().getPersons();
    }

    /**
     * Finds a person by first and last name.
     *
     * @param firstName first name of the person
     * @param lastName  last name of the person
     * @return an Optional containing the {@link Person} if found, otherwise empty
     */
    public Optional<Person> findByName(String firstName, String lastName) {
        return jsonRepo.getData().getPersons().stream()
                .filter(p ->
                        p.getFirstName().equalsIgnoreCase(firstName) &&
                                p.getLastName().equalsIgnoreCase(lastName)
                )
                .findFirst();
    }

    /**
     * Saves a new person to the JSON data store.
     *
     * @param person the {@link Person} object to save
     */
    public void save(Person person) {
        jsonRepo.getData().getPersons().add(person);
        jsonRepo.persist();
    }

    /**
     * Deletes a person from the JSON data store.
     *
     * @param person the {@link Person} object to delete
     */
    public void delete(Person person) {
        jsonRepo.getData().getPersons().remove(person);
        jsonRepo.persist();
    }

    /**
     * Persists the current state of the persons data to storage.
     */
    public void persist() {
        jsonRepo.persist();
    }
}
