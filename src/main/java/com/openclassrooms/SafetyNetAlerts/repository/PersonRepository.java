package com.openclassrooms.SafetyNetAlerts.repository;

import com.openclassrooms.SafetyNetAlerts.JsonDataLoader;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepository {

    private final JsonDataLoader loader;

    public PersonRepository(JsonDataLoader loader) {
        this.loader = loader;
    }

    // Return the live list (for now). Service layer controls how it's used.
    public List<Person> findAll() {
        return loader.getPersons();
    }

    public Optional<Person> findByName(String firstName, String lastName) {
        return loader.getPersons().stream()
                .filter(p -> p.getFirstName().equalsIgnoreCase(firstName)
                        && p.getLastName().equalsIgnoreCase(lastName))
                .findFirst();
    }

    public void save(Person person) {
        loader.getPersons().add(person);
    }

    public void delete(Person person) {
        loader.getPersons().remove(person);
    }
}