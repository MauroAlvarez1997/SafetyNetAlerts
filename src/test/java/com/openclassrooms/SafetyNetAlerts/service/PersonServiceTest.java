package com.openclassrooms.SafetyNetAlerts.service;

import com.openclassrooms.SafetyNetAlerts.dto.PersonDTO;
import com.openclassrooms.SafetyNetAlerts.exceptions.PersonNotFoundException;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import com.openclassrooms.SafetyNetAlerts.repository.PersonRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("PersonService Tests")
class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    @DisplayName("Should return all persons as DTOs")
    void getAllPersons_shouldReturnMappedDtos() {
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Boyd");
        person.setAddress("1509 Culver St");
        person.setCity("Culver");
        person.setZip("97451");
        person.setPhone("841-874-6512");
        person.setEmail("jaboyd@email.com");

        when(personRepository.findAll()).thenReturn(List.of(person));

        List<PersonDTO> result = personService.getAllPersons();

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals("Boyd", result.get(0).getLastName());
        assertEquals("1509 Culver St", result.get(0).getAddress());
    }

    @Test
    @DisplayName("Should save and return new person as DTO")
    void addPerson_shouldSaveAndReturnDto() {
        Person input = new Person();
        input.setFirstName("Jane");
        input.setLastName("Doe");
        input.setAddress("123 Main St");

        PersonDTO result = personService.addPerson(input);

        verify(personRepository, times(1)).save(any(Person.class));
        assertEquals("Jane", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("123 Main St", result.getAddress());
    }

    @Test
    @DisplayName("Should update existing person fields and persist changes")
    void updatePerson_shouldUpdateFieldsAndPersist() {
        Person existing = new Person();
        existing.setFirstName("John");
        existing.setLastName("Boyd");
        existing.setAddress("Old Address");
        existing.setCity("Old City");
        existing.setZip("00000");
        existing.setPhone("000");
        existing.setEmail("old@email.com");

        Person update = new Person();
        update.setAddress("New Address");
        update.setCity("New City");
        update.setZip("11111");
        update.setPhone("111");
        update.setEmail("new@email.com");

        when(personRepository.findByName("John", "Boyd"))
                .thenReturn(Optional.of(existing));

        PersonDTO result =
                personService.updatePerson("John", "Boyd", update);

        assertEquals("New Address", result.getAddress());
        assertEquals("New City", result.getCity());
        assertEquals("11111", result.getZip());
        assertEquals("111", result.getPhone());
        assertEquals("new@email.com", result.getEmail());

        verify(personRepository, times(1)).persist();
    }

    @Test
    @DisplayName("Should throw PersonNotFoundException when updating non-existent person")
    void updatePerson_shouldThrowException_whenNotFound() {
        when(personRepository.findByName("Jane", "Doe"))
                .thenReturn(Optional.empty());

        Person update = new Person();
        update.setAddress("New Address");

        assertThrows(
                PersonNotFoundException.class,
                () -> personService.updatePerson("Jane", "Doe", update)
        );

        verify(personRepository, never()).persist();
    }

    @Test
    @DisplayName("Should delete existing person")
    void deletePerson_shouldDeleteExistingPerson() {
        Person existing = new Person();
        existing.setFirstName("John");
        existing.setLastName("Boyd");

        when(personRepository.findByName("John", "Boyd"))
                .thenReturn(Optional.of(existing));

        personService.deletePerson("John", "Boyd");

        verify(personRepository, times(1)).delete(existing);
    }

    @Test
    @DisplayName("Should throw PersonNotFoundException when deleting non-existent person")
    void deletePerson_shouldThrowException_whenNotFound() {
        when(personRepository.findByName("Jane", "Doe"))
                .thenReturn(Optional.empty());

        assertThrows(
                PersonNotFoundException.class,
                () -> personService.deletePerson("Jane", "Doe")
        );

        verify(personRepository, never()).delete(any());
    }
}
