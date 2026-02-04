package com.openclassrooms.SafetyNetAlerts.service;

import com.openclassrooms.SafetyNetAlerts.dto.PersonInfoDTO;
import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import com.openclassrooms.SafetyNetAlerts.repository.PersonRepository;
import com.openclassrooms.SafetyNetAlerts.service.PersonMedicalInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("PersonInfoService Tests")
class PersonInfoServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonLookupService lookupService;

    @InjectMocks
    private PersonInfoService personInfoService;

    @Test
    @DisplayName("Should return all persons with matching last name including their medical information")
    void getPersonsByLastName_shouldReturnAllMatchingPersons() {

        // ---- Person data ----
        Person person1 = new Person();
        person1.setFirstName("John");
        person1.setLastName("Boyd");
        person1.setAddress("1509 Culver St");

        Person person2 = new Person();
        person2.setFirstName("Jane");
        person2.setLastName("Boyd");
        person2.setAddress("29 15th St");

        when(personRepository.findAll()).thenReturn(List.of(person1, person2));

        // ---- PersonMedicalInfo data for person1 ----
        MedicalRecord record1 = new MedicalRecord();
        record1.setBirthdate("03/06/1984");
        record1.setMedications(List.of("aznol:350mg"));
        record1.setAllergies(List.of("nillacilan"));

        PersonMedicalInfo info1 = new PersonMedicalInfo(person1, 39, record1);

        when(lookupService.findByAddress("1509 Culver St")).thenReturn(List.of(info1));

        // ---- PersonMedicalInfo data for person2 ----
        MedicalRecord record2 = new MedicalRecord();
        record2.setBirthdate("04/06/1988");
        record2.setMedications(List.of("hydrapermazol:100mg"));
        record2.setAllergies(List.of("peanut"));

        PersonMedicalInfo info2 = new PersonMedicalInfo(person2, 35, record2);

        when(lookupService.findByAddress("29 15th St")).thenReturn(List.of(info2));

        // ---- Execute ----
        List<PersonInfoDTO> result = personInfoService.getPersonsByLastName("Boyd");

        // ---- Verify ----
        assertEquals(2, result.size());

        PersonInfoDTO dto1 = result.stream()
                .filter(p -> p.getFirstName().equals("John"))
                .findFirst()
                .orElseThrow();
        assertEquals("Boyd", dto1.getLastName());
        assertEquals(39, dto1.getAge());
        assertEquals(1, dto1.getMedications().size());
        assertEquals(1, dto1.getAllergies().size());

        PersonInfoDTO dto2 = result.stream()
                .filter(p -> p.getFirstName().equals("Jane"))
                .findFirst()
                .orElseThrow();
        assertEquals("Boyd", dto2.getLastName());
        assertEquals(35, dto2.getAge());
        assertEquals(1, dto2.getMedications().size());
        assertEquals(1, dto2.getAllergies().size());

        // ---- Verify mocks ----
        verify(personRepository, times(1)).findAll();
        verify(lookupService, times(1)).findByAddress("1509 Culver St");
        verify(lookupService, times(1)).findByAddress("29 15th St");
    }
}
