package com.openclassrooms.SafetyNetAlerts.repository;

import com.openclassrooms.SafetyNetAlerts.model.JsonData;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("PersonRepository Tests")
class PersonRepositoryTest {

    @Mock
    private JsonDataRepository jsonDataRepository;

    @InjectMocks
    private PersonRepository personRepository;

    private JsonData jsonData;
    private List<Person> persons;

    @BeforeEach
    void setUp() {
        persons = new ArrayList<>();

        jsonData = new JsonData();
        jsonData.setPersons(persons);
    }

    @Test
    @DisplayName("Should return all persons from repository")
    void findAll_shouldReturnAllPersons() {
        when(jsonDataRepository.getData()).thenReturn(jsonData);

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Boyd");

        persons.add(person);

        List<Person> result = personRepository.findAll();

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
    }

    @Test
    @DisplayName("Should return person when found by name (case-insensitive)")
    void findByName_shouldReturnMatchingPerson_whenFound() {
        when(jsonDataRepository.getData()).thenReturn(jsonData);

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Boyd");

        persons.add(person);

        Optional<Person> result = personRepository.findByName("john", "boyd");

        assertTrue(result.isPresent());
        assertEquals("John", result.get().getFirstName());
        assertEquals("Boyd", result.get().getLastName());
    }

    @Test
    @DisplayName("Should return empty Optional when person not found by name")
    void findByName_shouldReturnEmpty_whenNotFound() {
        when(jsonDataRepository.getData()).thenReturn(jsonData);

        Optional<Person> result = personRepository.findByName("Jane", "Doe");

        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should save person and persist to data source")
    void save_shouldAddPersonAndPersist() {
        when(jsonDataRepository.getData()).thenReturn(jsonData);

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Boyd");

        personRepository.save(person);

        assertEquals(1, persons.size());
        verify(jsonDataRepository, times(1)).persist();
    }

    @Test
    @DisplayName("Should delete person and persist changes")
    void delete_shouldRemovePersonAndPersist() {
        when(jsonDataRepository.getData()).thenReturn(jsonData);

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Boyd");

        persons.add(person);

        personRepository.delete(person);

        assertTrue(persons.isEmpty());
        verify(jsonDataRepository, times(1)).persist();
    }

    @Test
    @DisplayName("Should delegate persist call to JsonDataRepository")
    void persist_shouldCallJsonRepositoryPersist() {
        personRepository.persist();

        verify(jsonDataRepository, times(1)).persist();
    }

    @Test
    @DisplayName("Should return all persons at given address (case-insensitive)")
    void findByAddress_shouldReturnMatchingPersons() {
        when(jsonDataRepository.getData()).thenReturn(jsonData);

        Person p1 = new Person();
        p1.setFirstName("John");
        p1.setAddress("1509 Culver St");

        Person p2 = new Person();
        p2.setFirstName("Jane");
        p2.setAddress("Other Address");

        persons.add(p1);
        persons.add(p2);

        List<Person> result =
                personRepository.findByAddress("1509 culver st");

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
    }

}
