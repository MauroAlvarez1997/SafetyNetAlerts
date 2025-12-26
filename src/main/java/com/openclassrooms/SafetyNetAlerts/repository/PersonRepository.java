package com.openclassrooms.SafetyNetAlerts.repository;

import com.openclassrooms.SafetyNetAlerts.model.Person;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepository {

    private final JsonDataRepository jsonRepo;

    public PersonRepository(JsonDataRepository jsonRepo) {
        this.jsonRepo = jsonRepo;
    }

    public List<Person> findAll() {
        return jsonRepo.getData().getPersons();
    }

    public Optional<Person> findByName(String firstName, String lastName) {
        return jsonRepo.getData().getPersons().stream()
                .filter(p ->
                        p.getFirstName().equalsIgnoreCase(firstName) &&
                                p.getLastName().equalsIgnoreCase(lastName)
                )
                .findFirst();
    }

    public void save(Person person) {
        jsonRepo.getData().getPersons().add(person);
        jsonRepo.persist();
    }

    public void delete(Person person) {
        jsonRepo.getData().getPersons().remove(person);
        jsonRepo.persist();
    }

    public void persist(){
        jsonRepo.persist();
    }
}